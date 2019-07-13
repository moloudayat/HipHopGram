package com.hiphopgam.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AutoStart extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Alarm alarm =new Alarm();

        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            alarm.setAlarm(context);
            Toast.makeText(context,"clock start!!!",Toast.LENGTH_LONG).show();
        }
    }
}
