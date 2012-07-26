package cuong.server;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendSMS extends Activity {
	
	public String str = "";
	public String k = "";
	public String number = "";
	public String message = "";
	@Override
	public void onNewIntent(Intent intent1) {
    Log.d("MapsActivity", "onNewIntent is called!");
    str = intent1.getStringExtra("KEY").toString();
    super.onNewIntent(intent1); 
    char[] arr = str.toCharArray();
    k += arr[0];
    int m = Integer.parseInt(k);
    int n = arr.length;
    for(int i = 0; i < n; i++)
    {
    	if ((i>0) && (i<m+1)) number += arr[i];
    	if((i>m) && (i<n)) message += arr[i];
    }
    
    sendSms(number,message);
}
	
	//String number = "+841649610768";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }   
    private void sendSms(String phoneNumber, String message)
    {
    	
    	String SENT="SMS_SENT";
    	String DELIVERED="SMS_DELIVERED";
    	
    	PendingIntent pi = PendingIntent.getActivity(this, 0,
                new Intent(this, SendSMS.class), 0);                
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNumber, null, message, pi, null);        
          
    }
}
