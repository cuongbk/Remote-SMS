package cuong.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import cuong.server.ServerActivity;

public class InCallActivity extends Activity {
    /** Called when the activity is first created. */
	
	public String No;
	@Override
	public void onNewIntent(Intent intent1) {
    Log.d("MapsActivity", "onNewIntent is called!");
    No = intent1.getStringExtra("KEY").toString();
    super.onNewIntent(intent1); 
    String number = "N"+No;
    Toast.makeText(getBaseContext(), No, Toast.LENGTH_LONG).show();
    try {
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(ServerActivity.client.getOutputStream()));
		out.write(number);
		out.flush();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
              
        //Toast.makeText(getApplicationContext(), number, Toast.LENGTH_SHORT).show();
    }
}