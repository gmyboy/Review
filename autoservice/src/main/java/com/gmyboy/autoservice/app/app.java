package com.gmyboy.autoservice.app;

import android.app.Application;

import com.gmyboy.autoservice.rest.RestClient;

/**
 * Created by GMY on 2017-01-05 13:08.
 * Contact me via email igmyboy@gmail.com.
 */
public class App extends Application {
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
