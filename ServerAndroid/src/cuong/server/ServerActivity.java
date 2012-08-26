package cuong.server;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;
import org.apache.http.conn.ClientConnectionManager;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class ServerActivity extends Activity {
	 
    private TextView serverStatus;
    public static Socket client = null;
    
    // default ip
    public static String SERVERIP = "10.0.2.15";
 
    // designate a port
    public static final int SERVERPORT = 8080;
 
    private Handler handler = new Handler();
 
    private ServerSocket serverSocket;
    
    private void sendSMS(String phoneNumber, String message)
    {
    	
    	String SENT="SMS_SENT";
    	String DELIVERED="SMS_DELIVERED";
    	
    	PendingIntent pi = PendingIntent.getActivity(this, 0,
                new Intent(this, ServerThread.class), 0);                
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNumber, null, message, pi, null);        
          
    }

 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button btnExit = (Button)findViewById(R.id.exit);
        btnExit.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				finish();
				
			}
		});
        
        Button btnAbout = (Button) findViewById(R.id.help);
        btnAbout.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ServerActivity.this, Help.class);
				startActivity(i);
			}
        	
        });
        serverStatus = (TextView) findViewById(R.id.status);
 
        SERVERIP = getLocalIpAddress();
 
        Thread fst = new Thread(new ServerThread());
        fst.start();
    }
 
    public class ServerThread implements Runnable {
    	public String line = null;
    	
 
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
 
                        try {
                            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                            
                            while ((line = in.readLine()) != null) {
                                
                                handler.post(new Runnable() {
                                    public void run() {
                                        //Toast.makeText(getBaseContext(), line, Toast.LENGTH_LONG).show();
              
                                        char[] arr = line.toCharArray();
                                        String k = "";
                    		     	    k += arr[0];
                    		     	    k += arr[1];
                    		     	    int m = Integer.valueOf(k);
                    		     	    int n = arr.length;
                    		     	    String number = "";
                    		     	    String message = "";
                    		     	    for(int i = 0; i < n; i++)
                    		     	    {
                    		     	    	if ((i>1) && (i< m+2)) number += arr[i];
                    		     	    	
                    		     	    }
                    		     	    for(int i = 0; i < n; i++)
                    		     	    {
                    		     	    	if((i> m+2) && (i<n)) message += arr[i];
                    		     	    }
                    		     	    
                    		     	    Toast.makeText(getBaseContext(), number, Toast.LENGTH_LONG).show();
                    		     	    
                    		     	    
                    		     	   sendSMS(number,message);
                                    }
                                    
                                });
                            }
                            break;
                        } catch (Exception e) {
                            handler.post(new Runnable() {
                                public void run() {
                                    serverStatus.setText("Oops. Connection interrupted. Please reconnect your phones.");
                                }
                            });
                            e.printStackTrace();
                        }
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
