package com.gmyboy.review.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 流式布局Flow
 */
public class FlowLayout extends ViewGroup {

    //存储所有子View
    private List<List<View>> mAllChildViews = new ArrayList<>();
    //每一行的高度
    private List<Integer> mLineHeight = new ArrayList<>();

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Ask all children to measure themselves and compute the measurement of this
     * layout based on the children.
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //父控件传进来的宽度和高度以及对应的测量模式
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        //如果当前ViewGroup的宽高为wrap_content的情况
        int width = 0;//自己测量的宽度结果
        int height = 0;//自己测量的高度结果
        //记录每一行的宽度和高度
        int lineWidth = 0;
        int lineHeight = 0;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            // Measure the child.测量子view的宽高
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
            //得到LayoutParams
            MarginLayoutParams layoutParams = (MarginLayoutParams) getLayoutParams();
            //子view占据宽度
            int childWidth = child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            //子view占据高度
            int childHeight = child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;

            //换行
            if (lineWidth + childWidth > sizeWidth) {
                //对比得到最大的宽度
                width = Math.max(width, lineWidth);
                //重置lineWidth
                lineWidth = childWidth;
                //记录行高(换行)
                height += lineHeight;
                lineHeight = childHeight;
            } else {//不换行情况
                //叠加行宽
                lineWidth += childWidth;
                //得到最大行高
                lineHeight = Math.max(lineHeight, childHeight);
            }
            //处理最后一个子View的情况
            if (i == childCount - 1) {
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }
            //wrap_content
            setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width, modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    /**
     * Position all children within this layout.
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mAllChildViews.clear();
        mLineHeight.clear();
        //获取当前ViewGroup的宽度
        int width = getWidth();

        int lineWidth = 0;
        int lineHeight = 0;
        //记录当前行的view
        List<View> lineViews = new ArrayList<>();

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            //如果需要换行
            if (childWidth + lineWidth + lp.leftMargin + lp.rightMargin > width) {
                //记录LineHeight
                mLineHeight.add(lineHeight);
                //记录当前行的Views
                mAllChildViews.add(lineViews);
                //重置行的宽高
                lineWidth = 0;
                lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
                //重置view的集合
                lineViews = new ArrayList();
            }
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            lineHeight = Math.max(lineHeight, childHeight + lp.topMargin + lp.bottomMargin);
            lineViews.add(child);
        }
        //处理最后一行
        mLineHeight.add(lineHeight);
        mAllChildViews.add(lineViews);

        //设置子View的位置
        int left = 0;
        int top = 0;
        //获取行数
        int lineCount = mAllChildViews.size();
        for (int i = 0; i < lineCount; i++) {
            //当前行的views和高度
            lineViews = mAllChildViews.get(i);
            lineHeight = mLineHeight.get(i);
            for (int j = 0; j < lineViews.size(); j++) {
                View child = lineViews.get(j);
                //判断是否显示
                if (child.getVisibility() == View.GONE) {
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                int cLeft = left + lp.leftMargin;
                int cTop = top + lp.topMargin;
                int cRight = cLeft + child.getMeasuredWidth();
                int cBottom = cTop + child.getMeasuredHeight();
                //进行子View进行布局
                child.layout(cLeft, cTop, cRight, cBottom);
                left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }
            left = 0;
            top += lineHeight;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
