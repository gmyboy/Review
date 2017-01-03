package com.chudong.gpuimagetest;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageView;

import com.chudong.gpuimagetest.GPUImageFilterTools.FilterAdjuster;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    GPUImageView imageView;

    @OnClick(R.id.sepia)
    void switchToSepia() {
        GPUImageFilterTools.showDialog(this, new GPUImageFilterTools.OnGpuImageFilterChosenListener() {
            @Override
            public void onGpuImageFilterChosenListener(GPUImageFilter filter) {
                switchFilterTo(filter);
                imageView.requestRender();
            }


        });
    }


    @BindView(R.id.gray)
    Button gray;
    @BindView(R.id.sharp)
    Button sharp;
    @BindView(R.id.edge)
    Button edge;

    private Looper mImageLooper;
    private ImageHandler mImageHandler;
    private GPUImageFilter mFilter;
    private FilterAdjuster mFilterAdjuster;

    private final class ImageHandler extends Handler {
        public ImageHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setupImage();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Uri mUrl = Uri.parse("https://media.giphy.com/media/l3vRldJHrMcrt8lHi/giphy.gif");
        initHandler();
        mImageHandler.sendEmptyMessage(1);
    }

    private void initHandler() {
        HandlerThread mThread = new HandlerThread("ServiceSetImage", Process.THREAD_PRIORITY_BACKGROUND);
        mThread.start();

        mImageLooper = mThread.getLooper();
        mImageHandler = new ImageHandler(mImageLooper);
    }

    private void setupImage() {
        // 读取图像
        AssetManager as = getAssets();
        InputStream is = null;
        Bitmap bitmap = null;
        try {
            is = as.open("test2.jpg");
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            Log.e("MainActivity", "Error");
        }

        imageView.setImage(bitmap);
    }

    private void switchFilterTo(GPUImageFilter filter) {
        if (mFilter == null
                || (filter != null && !mFilter.getClass().equals(filter.getClass()))) {
            mFilter = filter;
            imageView.setFilter(mFilter);
            mFilterAdjuster = new FilterAdjuster(mFilter);

//            findViewById(R.id.seekBar).setVisibility(
//                    mFilterAdjuster.canAdjust() ? View.VISIBLE : View.GONE);
        }
    }
}
