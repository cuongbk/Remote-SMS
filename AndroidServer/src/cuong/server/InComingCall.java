package cuong.server;

import cuong.server.InCallActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class InComingCall extends BroadcastReceiver {

	private static final String TAG = "CustomBroadcastReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
	    Log.v(TAG, "WE ARE INSIDE!!!!!!!!!!!");
	    TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
	    CustomPhoneStateListener customPhoneListener = new CustomPhoneStateListener();
	    telephony.listen(customPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
	    Bundle bundle = intent.getExtras();
	    String number = bundle.getString("incoming_number");
	    Toast.makeText(context, number, Toast.LENGTH_SHORT).show();
	    
	    
	    Intent i = new Intent(context, InCallActivity.class);
	    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
	    String name = "KEY";
	    String value = number;
	    i.putExtra(name, value);
	    context.startActivity(i);

	}	
}
