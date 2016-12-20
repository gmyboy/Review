package com.gmyboy.review.fragment.behavior;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by GMY on 2016-12-20 17:38.
 * Contact me via email igmyboy@gmail.com.
 */
public class ScrollBehavior extends CoordinatorLayout.Behavior<View> {
    public ScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            float abs = Math.abs(dependency.getTranslationY());
            child.setTranslationY(abs);
        }
        return true;
    }
}
