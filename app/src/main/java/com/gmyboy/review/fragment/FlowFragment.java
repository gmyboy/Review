package com.gmyboy.review.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmyboy.review.R;
import com.gmyboy.review.view.FlowLayout;

import butterknife.BindView;

/**
 * 流式布局展示页面
 * Created by GMY on 2016-12-06 17:37.
 * Contact me via email igmyboy@gmail.com.
 */
public class FlowFragment extends Fragment {

    @BindView(R.id.flowlayout)
    FlowLayout flowlayout;
    private String mNames[] = {
            "welcome", "android", "TextView",
            "apple", "jamy", "kobe bryant",
            "jordan", "layout", "viewgroup",
            "margin", "padding", "text",
            "name", "type", "search", "logcat"
    };

    public static FlowFragment newInstance() {
        FlowFragment fragment = new FlowFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.frag_flow,container,false);
        for (int i = 0; i < mNames.length; i++) {
            TextView tv = new TextView(getContext());
            tv.setText(mNames[i]);
            tv.setTextColor(Color.WHITE);
//            tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_bg));
            flowlayout.addView(view, container.getLayoutParams());
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;
        for (int i = 0; i < mNames.length; i++) {
            TextView tv = new TextView(getContext());
            tv.setText(mNames[i]);
            tv.setTextColor(Color.WHITE);
//            tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_bg));
            flowlayout.addView(view, lp);
        }
    }

}
