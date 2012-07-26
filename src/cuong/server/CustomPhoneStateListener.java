package cuong.server;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class CustomPhoneStateListener extends PhoneStateListener {

   private static final String TAG = "CustomPhoneStateListener";

   public void onCallStateChange(int state, String incomingNumber){

    Log.v(TAG, "WE ARE INSIDE!!!!!!!!!!!");
    Log.v(TAG, incomingNumber);

    switch(state){
        case TelephonyManager.CALL_STATE_RINGING:
            Log.d(TAG, "RINGING");
            break;
    } 
   }
}