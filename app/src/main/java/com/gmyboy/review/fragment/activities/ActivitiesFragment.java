package com.gmyboy.review.fragment.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.gmyboy.review.R;
import com.gmyboy.review.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by GMY on 2016-12-06 11:23.
 * Contact me via email igmyboy@gmail.com.
 */
public class ActivitiesFragment extends BaseFragment {
    private static final int REQUEST_CODE = 0;

    @BindView(R.id.tv_backstr)
    TextView tvBackStr;
    @OnClick(R.id.btn)
    void toSecond(View view) {
        Intent intent = new Intent(getContext(), SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("tostr", "tostr");
        intent.putExtras(bundle);
        startActivityForResult(intent,REQUEST_CODE);
    }

    public static ActivitiesFragment newInstance() {
        ActivitiesFragment fragment = new ActivitiesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public int bindView() {
        return R.layout.frag_activity;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE){
            if(resultCode== Activity.RESULT_OK){
                tvBackStr.setText(String.valueOf(data.getExtras().getString("returnstr")));
            }
        }
    }
}
