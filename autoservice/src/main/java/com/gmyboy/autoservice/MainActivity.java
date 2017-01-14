package com.gmyboy.autoservice;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.video)
    VideoView video;

    private List<String> datas;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        Intent mIntent = new Intent(this, MyService.class);
//        startService(mIntent);
        datas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            datas.add("acnddcdf" + i);
        }


        myAdapter = new MyAdapter(datas);
        myAdapter.setListener(new MyAdapter.onBtnClickListener() {
            @Override
            public void onBtn1(View v, int position) {
                Toast.makeText(MainActivity.this, "btn1--" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBtn2(View v, int position) {
                Toast.makeText(MainActivity.this, "btn2--" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBtn3(View v, int position) {
                Toast.makeText(MainActivity.this, "btn3--" + position, Toast.LENGTH_SHORT).show();
            }
        });
//        recycleView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(layoutManager);
        recycleView.setAdapter(myAdapter);
//        Observable.just("one");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu1:
                Intent mIntent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(mIntent);
                break;
            case R.id.menu2:
                dispatchTakeVideoIntent();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    static final int REQUEST_VIDEO_CAPTURE = 1;

    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = intent.getData();
            video.setVideoURI(videoUri);
        }
    }

}
