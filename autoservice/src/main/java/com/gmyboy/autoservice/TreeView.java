package com.gmyboy.autoservice;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.gmyboy.autoservice.utils.GLog;

/**
 * Created by gmy on 2017/1/13.
 * E-mail me via gmyboy@qq.com
 */

public class TreeView extends ViewGroup {

    private int radius = 300;
    private ViewDragHelper mDragHelper;

    public TreeView(Context context) {
        super(context);
    }

    public TreeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TreeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.MyView, defStyleAttr, 0);


        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        radius = a.getDimensionPixelSize(
                R.styleable.MyView_exampleDimension,
                radius);

        a.recycle();
        float density = context.getResources().getDisplayMetrics().density;
        mDragHelper = ViewDragHelper.create(this, 0.5f, new DragHelpCallback());
        mDragHelper.setMinVelocity(density);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        GLog.e("view son counts:" + childCount);
        int itemH = 0;
//        Rect rect = new Rect();
        //圆心坐标
        float[] centre = {getWidth() / 2 * 1.0f, getHeight() / 2 * 1.0f};

        //每个占多少个弧度
//        float degreeLength = 360/cCount*1.0f;
        float degreeLength = (float) (2 * Math.PI / childCount * 1.0f);

        float[][] xyPosition = new float[childCount][2];

        for (int i = 0; i < childCount; i++) {
            xyPosition[i] = getXYPoint(centre, radius, degreeLength * (i));

            //x坐标
            int xLabel = (int) xyPosition[i][0];
            //y坐标
            int yLabel = (int) xyPosition[i][1];

            GLog.e("position : (" + xLabel + "," + yLabel + ")");
            View view = getChildAt(i);
            int left = (int) (xLabel - view.getMeasuredWidth() / 2 * 1.0f);
            int top = (int) (yLabel - view.getMeasuredHeight() / 2 * 1.0f);
            setChildFrame(view, left, top, view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }

    private void setChildFrame(View child, int left, int top, int width, int height) {
        child.layout(left, top, left + width, top + height);
    }


    /**
     * 以原点为圆点，以radius维半径画圆，通过弧度o,获得坐标
     *
     * @param radius 半径
     * @param o      弧度
     * @return
     */
    public static float[] getXYPoint(float[] centrePoint, int radius, float o) {
        GLog.e("o: " + o);
        GLog.e("radius: " + radius);
        GLog.e("centrePoint: [" + centrePoint[0] + "," + centrePoint[1] + "]");
        float[] xyPoint = {(float) (radius * Math.sin(o) + centrePoint[0]),
                (float) ((-1) * radius * Math.cos(o) + centrePoint[1])};
        return xyPoint;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        GLog.e("TreeView onTouchEvent" + event.toString());
        if (!isEnabled()) {
            return super.onTouchEvent(event);
        }
        try {
            mDragHelper.processTouchEvent(event);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);
        GLog.e("TreeView dispatchTouchEvent" + action);
        if (!isEnabled()) {
            mDragHelper.abort();
            return super.dispatchTouchEvent(ev);
        }
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mDragHelper.cancel();
            return false;
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);
        GLog.e("TreeView onInterceptTouchEvent" + action);
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mDragHelper.cancel();
            return false;
        }
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    private class DragHelpCallback extends ViewDragHelper.Callback {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return false;
        }

        @Override
        public void onViewDragStateChanged(int state) {
            GLog.e(state + "");
            super.onViewDragStateChanged(state);
        }
    }

    public class LayoutParams extends ViewGroup.MarginLayoutParams {

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }
}
