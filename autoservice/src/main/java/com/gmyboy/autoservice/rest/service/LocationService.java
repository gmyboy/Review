package com.gmyboy.autoservice.rest.service;

import com.gmyboy.autoservice.rest.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by GMY on 2017-01-05 13:25.
 * Contact me via email igmyboy@gmail.com.
 */
public interface LocationService {
    @FormUrlEncoded
    @POST("position/refresh")
    Call<ApiResponse> setPos(@Field("lon") String longitude, @Field("lat") String latitude, @Field("location") String location, @Field("client") String client);
}
