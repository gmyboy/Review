package com.gmyboy.autoservice;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.gmyboy.autoservice.utils.GLog;

import retrofit2.Response;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * helper methods.
 */
public class MyIntentService extends IntentService {
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.gmyboy.autoservice.fragment.services.action.FOO";
    private static final String ACTION_BAZ = "com.gmyboy.autoservice.fragment.services.action.BAZ";

    private static final String EXTRA_PARAM1 = "com.gmyboy.autoservice.fragment.services.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.gmyboy.autoservice.fragment.services.extra.PARAM2";

    private static final String LONGITUDE = "com.gmyboy.autoservice.extra.longitude";
    private static final String LATITUDE = "com.gmyboy.autoservice.extra.latitude";

    public MyIntentService() {
        //创建的默认工作线程的名称
        super("MyIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(LONGITUDE, param1);
        intent.putExtra(LATITUDE, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(LONGITUDE);
                final String param2 = intent.getStringExtra(LATITUDE);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String longitude, String latitude) {
        // Normally we would do some work here, like download a file.
        // For our sample, we just sleep for 5 seconds.
        try {
            GLog.e(longitude + "---" + latitude);
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

//            Response<ApiResponse> response = App.getRestClient().getLocationService().setPos(longitude, latitude).execute();
//            ApiResponse body = response.body();
//            GLog.e(body.toString());

        } catch (Exception e) {
            GLog.e(e.getMessage());
            // Restore interrupt status.
            Thread.currentThread().interrupt();
        }

        //intentservice默认实现了onbind，所以不需要手动stop
        //intentservice will stopself when work done
        //stopSelf();
    }
}
