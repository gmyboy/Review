package com.gmyboy.autoservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent mIntent = new Intent(this,MyService.class);
        startService(mIntent);
//        MyIntentService.startActionBaz(this, "intentservice", "startActionBaz");
    }
}
