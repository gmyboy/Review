package com.gmyboy.review.fragment.pattern.builder;

/**
 * Created by GMY on 2017-01-10 15:38.
 * Contact me via email igmyboy@gmail.com.
 */
public class DemoController {
    public int mTitle;
    public String mContent;
    public String mTip;

    public DemoController() {
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public String getmTip() {
        return mTip;
    }

    public void setmTip(String mTip) {
        this.mTip = mTip;
    }

    public int getmTitle() {
        return mTitle;
    }

    public void setmTitle(int mTitle) {
        this.mTitle = mTitle;
    }

    public static class DemoParams {
        public int mTitle;
        public String mContent;
        public String mTip;

        public void apply(DemoController mControl) {
            mControl.setmTitle(mTitle);
            mControl.setmContent(mContent);
            mControl.setmTip(mTip);
        }
    }

}
