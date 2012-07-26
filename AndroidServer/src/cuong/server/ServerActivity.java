package cuong.server;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class ServerActivity extends Activity {
	 
    private TextView serverStatus;
    Button btnExit;
    Button btnHelp;
    // default ip
    public static String SERVERIP = "192.168.88.105";
 
    // designate a port
    public static final int SERVERPORT = 9999;
    
    public static Socket client;
 
    private Handler handler = new Handler();
 
    private ServerSocket serverSocket;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btnHelp = (Button)findViewById(R.id.help);
        btnHelp.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ServerActivity.this, Help.class);
				startActivity(i);
			}
		});
        
        btnExit = (Button)findViewById(R.id.exit);
        btnExit.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
        
        serverStatus = (TextView) findViewById(R.id.status);
 
        SERVERIP = getLocalIpAddress();
 
        Thread fst = new Thread(new ServerThread());
        fst.start();
    }
 
    public class ServerThread implements Runnable {
        public void run() {
            try {
                if (SERVERIP != null) {
                    handler.post(new Runnable() {
                        public void run() {
                            serverStatus.setText("Listening on IP: " + SERVERIP);
                        }
                    });
                    serverSocket = new ServerSocket(SERVERPORT);
                  while (true) {
                        // listen for incoming clients
                    client = serverSocket.accept();
                    handler.post(new Runnable() {
                    public void run() {
                         serverStatus.setText("Connected.");
                       }
                    });
 
                Intent intent = new Intent(ServerActivity.this,SendSMS.class);
                startActivity(intent);
                  }
                } else {
                    handler.post(new Runnable() {
                        public void run() {
                            serverStatus.setText("Couldn't detect internet connection.");
                        }
                    });
                }
            } catch (Exception e) {
                handler.post(new Runnable() {
                    public void run() {
                        serverStatus.setText("Error");
                    }
                });
                e.printStackTrace();
            }
        }
    }
 
    // gets the ip address of your phone's network
    private String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) { return inetAddress.getHostAddress().toString(); }
                }
            }
        } catch (SocketException ex) {
            Log.e("ServerActivity", ex.toString());
        }
        return null;
    }
 
    @Override
    protected void onStop() {
        super.onStop();
        try {
             // make sure you close the socket upon exiting
             serverSocket.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
    }
 
}

