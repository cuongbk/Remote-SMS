package cuong.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.net.*;
import cuong.server.ServerActivity;

public class ReceiverSMS extends Activity {
	
	 @Override
		public void onNewIntent(Intent intent1) {
	    Log.d("MapsActivity", "onNewIntent is called!");
	    String message = intent1.getStringExtra("KEY").toString();
	    super.onNewIntent(intent1); 
	    //Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT).show();
	    
	    try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(ServerActivity.client.getOutputStream()));
			out.write(message);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	 }

	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        
	 }

}
