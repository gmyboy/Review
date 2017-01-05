package com.gmyboy.autoservice.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GMY on 2015/8/25 09:35.
 * Contact me via email gmyboy@qq.com.
 */
public class GLog {
    public List mList = new ArrayList();
    private static final char TOP_LEFT_CORNER = '╔';
    private static final char BOTTOM_LEFT_CORNER = '╚';
    private static final char MIDDLE_CORNER = '╟';
    private static final char HORIZONTAL_DOUBLE_LINE = '║';
    private static final String DOUBLE_DIVIDER = "════════════════════════════════════════════";
    private static final String SINGLE_DIVIDER = "────────────────────────────────────────────";
    private static final String TOP_BORDER = TOP_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String BOTTOM_BORDER = BOTTOM_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String MIDDLE_BORDER = MIDDLE_CORNER + SINGLE_DIVIDER + SINGLE_DIVIDER;

    public static void i(String msg) {
        if (isDebug()) {
            Log.i(getCallerName(), TOP_BORDER + "\n");
            Log.i(getCallerName(), HORIZONTAL_DOUBLE_LINE + msg + "\n");
            Log.i(getCallerName(), BOTTOM_BORDER);
        }
    }

    public static void d(String msg) {
        if (isDebug()) {
            Log.d(getCallerName(), TOP_BORDER + "\n");
            Log.d(getCallerName(), HORIZONTAL_DOUBLE_LINE + msg + "\n");
            Log.d(getCallerName(), BOTTOM_BORDER);
        }
    }

    public static void v(String msg) {
        if (isDebug()) {
            Log.v(getCallerName(), TOP_BORDER + "\n");
            Log.v(getCallerName(), HORIZONTAL_DOUBLE_LINE + msg + "\n");
            Log.v(getCallerName(), BOTTOM_BORDER);
        }
    }

    public static void e(String msg) {
        if (isDebug()) {
            Log.e(getCallerName(), TOP_BORDER + "\n");
            Log.e(getCallerName(), HORIZONTAL_DOUBLE_LINE + msg + "\n");
            Log.e(getCallerName(), BOTTOM_BORDER);
        }
    }

    public static void w(String msg) {
        if (isDebug()) {
            Log.w(getCallerName(), TOP_BORDER + "\n");
            Log.w(getCallerName(), HORIZONTAL_DOUBLE_LINE + msg + "\n");
            Log.w(getCallerName(), BOTTOM_BORDER);
        }
    }

    private static String getCallerName() {
        StackTraceElement[] elements = new Throwable().getStackTrace();
        return elements[2].getClassName();
    }

    private static Boolean isDebug() {
//        return BuildConfig.DEBUG;
        return true;
    }
}
