package com.gmyboy.autoservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.gmyboy.autoservice.utils.GLog;


public class MyServiceBrige extends Service {

    private MyBinder myBinder = new MyBinder();

    public class MyBinder extends Binder {
        MyServiceBrige getService() {
            return MyServiceBrige.this;
        }
    }

    @Override
    public void onCreate() {
        GLog.e("MyServiceBrige.onCreateService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        GLog.e("MyServiceBrige.onStartCommand");
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        GLog.e("MyServiceBrige.onBind");
        return myBinder;
    }

    //清理资源
    @Override
    public void onDestroy() {
        GLog.e("MyServiceBrige.onDestroy");
    }
}
