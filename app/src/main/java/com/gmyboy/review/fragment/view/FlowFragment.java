package com.gmyboy.review.fragment.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.gmyboy.review.R;
import com.gmyboy.review.fragment.BaseFragment;
import com.gmyboy.review.view.FlowLayout;

import butterknife.BindView;

/**
 * 流式布局展示页面
 * Created by GMY on 2016-12-06 17:37.
 * Contact me via email igmyboy@gmail.com.
 */
public class FlowFragment extends BaseFragment {

    @BindView(R.id.flowlayout)
    FlowLayout flowlayout;
    private String mNames[] = {
            "welcomewelcome", "android", "TextViewTextView",
            "appleappleappleappleappleapple", "jamyjamyjamyjamy", "kobebryant",
            "jordanjordanjordanjordan", "layoutlayout", "marginmarginmargin",
            "viewgroupviewgroupviewgroup", "paddingpadding", "texttexttext",
            "namenamename", "typetypetype", "searchsearchsearch", "logcat"
    };

    public static FlowFragment newInstance() {
        FlowFragment fragment = new FlowFragment();
        return fragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(2, 2, 2, 2);
        for (int i = 0; i < mNames.length; i++) {
            Button tv = new Button(getContext());
            tv.setText(mNames[i]);
//            tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_bg));
            flowlayout.addView(tv, lp);
        }

    }

    @Override
    public int bindView() {
        return R.layout.frag_flow;
    }
}
