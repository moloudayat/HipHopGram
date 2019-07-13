package com.hiphopgam.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class IncomingSms extends BroadcastReceiver {
    final SmsManager sms = SmsManager.getDefault();

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int i = 0; i < pdusObj.length; i++) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    String messege = currentMessage.getDisplayMessageBody();
                    Toast.makeText(context, phoneNumber + ": " + messege, Toast.LENGTH_LONG).show();
                    Log.d("-------------------phoneNumber---------------------", phoneNumber);
                    Log.d("-------------------messege---------------------", messege);
                }
            }
        } catch (Exception e) {
        }
    }
}

