package com.gmyboy.autoservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;

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
    private AMapLocationClientOption locationOption = null;

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

                Response<ApiResponse> response = App.getRestClient().getLocationService().setPos(String.valueOf(loc.getLongitude()), String.valueOf(loc.getLatitude())).execute();
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
        GLog.e("onCreate");
        //创建handler子线程
        HandlerThread mHandlerThread = new HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND);
        mHandlerThread.start();
        //get the HandlerThread's Looper for our handler
        mServiceLooper = mHandlerThread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);

        //初始化定位
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = new AMapLocationClientOption();
        // 设置定位模式为高精度模式
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        // 设置定位监听
        locationClient.setLocationListener(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        GLog.e("onStartCommand");
        Message message = mServiceHandler.obtainMessage();
        message.arg1 = startId;
        message.what = 1;
        mServiceHandler.sendMessage(message);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {

        // don't provide binding
        return null;
    }

    //清理资源
    @Override
    public void onDestroy() {
        GLog.e("onDestroy");
    }
}
