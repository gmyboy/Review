package com.gmyboy.autoservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.telephony.TelephonyManager;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.gmyboy.autoservice.app.App;
import com.gmyboy.autoservice.rest.model.ApiResponse;
import com.gmyboy.autoservice.utils.GLog;

import retrofit2.Response;


public class MyService extends Service implements AMapLocationListener {
    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;

    private AMapLocationClient locationClient = null;

    private TelephonyManager telecomManager;

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (null != aMapLocation) {
            Message msg = mServiceHandler.obtainMessage();
            msg.obj = aMapLocation;
            GLog.e("定位成功:" + aMapLocation.getLongitude() + "---" + aMapLocation.getLatitude());
            mServiceHandler.sendMessage(msg);
        }
    }

    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) return;
            try {
                AMapLocation loc = (AMapLocation) msg.obj;
                GLog.e(loc.getLongitude() + "---" + loc.getLatitude());
//            App.getRestClient().getLocationService().setPos(longitude, latitude, new Callback<ApiResponse>() {
//                @Override
//                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//                    GLog.e("done");
//                    ApiResponse body = response.body();
//                    GLog.e(body.toString());
//                }
//
//                @Override
//                public void onFailure(Call<ApiResponse> call, Throwable t) {
//                    GLog.e("fail" + t.getMessage());
//                }
//            });
                String imei = telecomManager.getDeviceId();
                Response<ApiResponse> response = App.getRestClient().getLocationService().setPos(String.valueOf(loc.getLongitude()), String.valueOf(loc.getLatitude()), loc.getAddress(), Build.MODEL + ":" + imei).execute();
                ApiResponse body = response.body();
                GLog.e(body.toString());

            } catch (Exception e) {
                GLog.e(e.getMessage());
                // Restore interrupt status.
                Thread.currentThread().interrupt();
            }

        }
    }

    @Override
    public void onCreate() {
        GLog.e("onCreateService");
        //创建handler子线程
        HandlerThread mHandlerThread = new HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND);
        mHandlerThread.start();
        //get the HandlerThread's Looper for our handler
        mServiceLooper = mHandlerThread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);

        telecomManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

        //初始化定位
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationClient.setLocationOption(getDefaultOption());
        // 设置定位监听
        locationClient.setLocationListener(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        GLog.e("onStartCommand");
        // 启动定位
        locationClient.startLocation();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {

        // don't provide binding
        return null;
    }

    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(30000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }

    //清理资源
    @Override
    public void onDestroy() {
        GLog.e("onDestroy");
    }
}
