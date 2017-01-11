package com.gmyboy.review.fragment.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.gmyboy.review.BaseActivity;
import com.gmyboy.review.MainActivity;
import com.gmyboy.review.R;
import com.gmyboy.review.util.GLog;

import butterknife.BindView;

/**
 * Created by GMY on 2016-12-06 14:36.
 * Contact me via email igmyboy@gmail.com.
 */
public class SecondActivity extends BaseActivity {
    @BindView(R.id.tv_second)
    TextView tvSecond;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("returnstr", "returnstr");
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        GLog.e("onBackPressed()");
        super.onBackPressed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLog.e("onCreate()");
        setContentView(R.layout.activity_second);
        tvSecond.setText(getIntent().getExtras().getString("tostr"));
    }

    @Override
    protected void onStop() {
        GLog.e("onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        GLog.e("onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        GLog.e("onPause()");
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GLog.e("onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        GLog.e("onStart()");
    }

    @Override
    protected void onRestart() {
        GLog.e("onRestart()");
        super.onRestart();
    }
}
