package com.gmyboy.mybuilder;

import android.content.res.Resources;

/**
 * Created by GMY on 2017-01-10 15:37.
 * Contact me via email igmyboy@gmail.com.
 */
public class Demo {

    final DemoController mControl;

    public Demo() {
        mControl = new DemoController();
    }

    public static class Builder {
        final DemoController.DemoParams params;

        public Builder() {
            params = new DemoController.DemoParams();
        }

        public Builder setTitle(int title) {
            params.mTitle = title;
            return this;
        }

        public Builder setContent(String msg) {
            params.mContent = msg;
            return this;
        }

        public Builder setTip(String tip) {
            params.mContent = tip;
            return this;
        }

        public Demo create() {
            final Demo demo = new Demo();
            params.apply(demo.mControl);
            return demo;
        }

    }
}
