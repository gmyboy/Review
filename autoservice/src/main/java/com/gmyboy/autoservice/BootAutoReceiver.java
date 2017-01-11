package com.gmyboy.autoservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by GMY on 2017-01-11 14:53.
 * Contact me via email igmyboy@gmail.com.
 */

public class BootAutoReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Intent mIntent = new Intent(context, MyService.class);
            context.startService(mIntent);
        }
    }
}
