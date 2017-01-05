package com.gmyboy.autoservice.rest.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * 请求返回信息
 * Created by GMY on 2017-01-05 13:27.
 * Contact me via email igmyboy@gmail.com.
 */
@Parcel
public class ApiResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("msg")
    private String msg;

    public int getCode() {
        return code;
    }

    public String getGeoPosition() {
        return msg;
    }

    @Override
    public String toString() {
        return code + "------" + msg;
    }
}
