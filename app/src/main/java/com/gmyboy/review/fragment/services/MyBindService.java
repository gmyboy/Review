package com.gmyboy.review.fragment.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class MyBindService extends Service {

    private final IBinder mBinder = new LocalBinder();
    private final Random mGernerator = new Random();

    public class LocalBinder extends Binder {
        MyBindService getService() {
            // Return this instance of LocalService so clients can call public methods
            return MyBindService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /** method for clients */
    public int getRandomNumber() {
        return mGernerator.nextInt(100);
    }
}
