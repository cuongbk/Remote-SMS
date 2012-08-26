package cuong.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.net.*;
import cuong.server.ServerActivity;

public class SendServer extends Activity {
	
	 @Override
		public void onNewIntent(Intent intent1) {
	    Log.d("MapsActivity", "onNewIntent is called!");
	    String message = intent1.getStringExtra("KEY").toString();
	    super.onNewIntent(intent1); 
	    Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
	    
	    try {
	    	PrintWriter out=new PrintWriter(ServerActivity.client.getOutputStream(),true);
			out.println(message);
			out.close();
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
