package com.gmyboy.review.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public class BaseFragment extends Fragment {
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
    public int bindView() {
        return 0;
    }

}
