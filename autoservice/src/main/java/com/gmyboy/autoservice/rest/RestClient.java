package com.gmyboy.autoservice.rest;

import com.gmyboy.autoservice.rest.service.LocationService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by GMY on 2017-01-05 14:13.
 * Contact me via email igmyboy@gmail.com.
 */
public class RestClient {
    private static final String BASE_URL = "http://a.ssei.cn/sseiserver/position/";
    private LocationService locationService;

    public RestClient() {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        locationService = mRetrofit.create(LocationService.class);
    }

    public LocationService getLocationService() {
        return locationService;
    }
}
