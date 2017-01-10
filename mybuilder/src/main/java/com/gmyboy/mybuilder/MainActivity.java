package com.gmyboy.mybuilder;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Demo mbuilder = new Demo.Builder()
                .setTitle(12)
                .setContent("fsdfdsfds")
                .setTip("sdfsdfds")
                .create();

        Demo.Builder builder =new Demo.Builder();
        builder.setTitle(123);
        builder.setContent("sdsdsds");
        builder.setTip("45646");
        builder.create();


//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setIcon(R.drawable.icon);
//        builder.setTitle("Title");
//        builder.setMessage("Message");
//        builder.setPositiveButton("Button1",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        setTitle("点击了对话框上的Button1");
//                    }
//                });
//        builder.setNeutralButton("Button2",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        setTitle("点击了对话框上的Button2");
//                    }
//                });
//        builder.setNegativeButton("Button3",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        setTitle("点击了对话框上的Button3");
//                    }
//                });
//        builder.create().show();  // 构建AlertDi
    }
}
