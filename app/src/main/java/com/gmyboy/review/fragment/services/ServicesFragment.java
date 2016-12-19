package com.gmyboy.review.fragment.services;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gmyboy.review.R;
import com.gmyboy.review.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by GMY on 2016-12-19 15:57.
 * Contact me via email igmyboy@gmail.com.
 */
public class ServicesFragment extends BaseFragment {
    @BindView(R.id.btn_startmyservice)
    Button btnStartmyservice;
    @BindView(R.id.btn_stopmyservice)
    Button btnStopmyservice;
    @BindView(R.id.btn_startIntentservice)
    Button btnStartIntentservice;

    @OnClick(R.id.btn_startmyservice)
    void startMyService(View v) {
        Intent intent = new Intent(getActivity(), MyService.class);
        getActivity().startService(intent);
    }

    @OnClick(R.id.btn_stopmyservice)
    void stopMyService(View v) {
        Intent intent = new Intent(getActivity(), MyService.class);
        getActivity().stopService(intent);
    }

    @OnClick(R.id.btn_startIntentservice)
    void startIntentService(View v) {
        MyIntentService.startActionBaz(getActivity(),"intentservice","startActionBaz");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int bindView() {
        return R.layout.frag_service;
    }

    public static ServicesFragment newInstance() {
        ServicesFragment fragment = new ServicesFragment();
        return fragment;
    }
}
