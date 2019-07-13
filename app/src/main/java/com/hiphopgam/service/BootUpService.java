package com.hiphopgam.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hiphopgam.ui.login.Login;

public class BootUpService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        intent= new Intent(context, Login.class);
        context.startActivity(intent);
    }
}
