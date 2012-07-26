package cuong.server;

import cuong.server.ReceiverSMS;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.view.View;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Bundle bundle = intent.getExtras();        
        SmsMessage[] msgs = null;
        String str = ""; 
        String number="";
        if (bundle != null)
        {
            //---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];            
            for (int i=0; i<msgs.length; i++){
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);                
                number +=  msgs[i].getOriginatingAddress();                     

                str += msgs[i].getMessageBody().toString();
                str += "\n"; 
            }
            char[] arr = number.toCharArray();
            int m = arr.length;
            String message = String.valueOf(m) + "SMS from " + number + ":" + str;
            Intent i = new Intent(context, ReceiverSMS.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            String name = "KEY";
            String value = message;
            i.putExtra(name, value);
            context.startActivity(i);
        }                         

	}

}
