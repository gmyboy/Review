package com.gmyboy.review.app;

import android.app.Application;

/**
 * Created by GMY on 2016-12-30 11:08.
 * Contact me via email igmyboy@gmail.com.
 */
public class SysApplication extends Application {
    private static SysApplication ourInstance = new SysApplication();

    public static SysApplication getInstance() {
        return ourInstance;
    }

    private SysApplication() {
    }
}
