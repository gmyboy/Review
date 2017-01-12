package com.gmyboy.autoservice.app;

import android.app.Application;

import com.gmyboy.autoservice.rest.RestClient;

/**
 * Created by gmy on 2017/1/12.
 * E-mail me via gmyboy@qq.com
 */

public class SysApplication extends Application {
    private static RestClient restClient;

    @Override
    public void onCreate() {
        super.onCreate();
        restClient = new RestClient();
    }

    public static RestClient getRestClient() {
        return restClient;
    }
}
