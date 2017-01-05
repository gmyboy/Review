package com.gmyboy.review.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;

import butterknife.ButterKnife;

/**
 * 声明为抽象类（不能被实例化）和子类必须继承的方法
 */
public abstract class BaseFragment extends Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(bindView(), container, false);
        }
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 通过id返回view
     *
     * @return
     */
    protected abstract int bindView();

}
