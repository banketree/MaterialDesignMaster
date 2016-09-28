package com.sk.collapse.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sk on 16-9-27.
 */

public class FlowLayout extends ViewGroup {

    private List<List<View>> mAllViews;
    private List<Integer> mLineHeights;

    private int mLeftMargin, mRightMargin, mTopMargin, mBottomMargin;

    public FlowLayout(Context context) {
        super(context);
        init(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {

        mAllViews = new ArrayList<>();
        mLineHeights = new ArrayList<>();

        mLeftMargin = 0;
        mRightMargin = 0;
        mTopMargin = 0;
        mBottomMargin = 0;
    }

    public void setMargins(int l, int t, int r, int b) {
        mLeftMargin = l;
        mTopMargin = t;
        mRightMargin = r;
        mBottomMargin = b;

        requestLayout();
    }

//    @Override
//    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
//        return new MarginLayoutParams(p);
//    }
//
//    @Override
//    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
//        return new MarginLayoutParams(getContext(), attrs);
//    }
//
//    @Override
//    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
//        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,
//                LayoutParams.MATCH_PARENT);
//    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int lineHeight = 0, lineWidth = 0;
        int width = 0, height = 0;
        int total = getChildCount();
        int i = 0;

        for(i = 0; i < total; i++) {

            View child = getChildAt(i);

            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) child.getLayoutParams();
            int leftMargin = mLeftMargin;
            int rightMargin = mRightMargin;

            int childWidth = child.getMeasuredWidth() + leftMargin + rightMargin;
            int childHeight = child.getMeasuredHeight() + leftMargin + rightMargin;

            if(lineWidth + childWidth > widthSize) {
                height += lineHeight;
                width = Math.max(lineWidth, childWidth);

                lineHeight = childHeight;
                lineWidth = childWidth;
            }else {
                lineWidth += childWidth;
                lineHeight = Math.max(childHeight, lineHeight);
            }

            if(i == total - 1) {

                width = Math.max(lineWidth, width);
                height += lineHeight;
            }
        }

        setMeasuredDimension(MeasureSpec.EXACTLY == widthMode ? widthSize : width,
                            MeasureSpec.EXACTLY == heightMode ? heightSize : height);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        mAllViews.clear();
        mLineHeights.clear();

        List<View> lineViews = new ArrayList<>();

        int total = getChildCount();
        int i = 0;

        int width = getWidth(), height = 0;
        int lineWidth = 0 ,lineHeight = 0;
        int top = 0;

        for(i = 0;i < total;i ++) {
            View child = getChildAt(i);
            //MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int leftMargin = mLeftMargin;
            int rightMargin = mRightMargin;
            int topMargin = mTopMargin;
            int bottomMargin = mBottomMargin;
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();


            if(childWidth + leftMargin + rightMargin + lineWidth > width) {

                mLineHeights.add(lineHeight);
                mAllViews.add(lineViews);

                lineWidth = 0;
                lineViews = new ArrayList<>();
            }

            lineWidth += childWidth + leftMargin + rightMargin;
            lineHeight = Math.max(lineHeight, childHeight + topMargin + bottomMargin);

            lineViews.add(child);

        }

        mLineHeights.add(lineHeight);
        mAllViews.add(lineViews);
		int linenum = mAllViews.size();
        for(int j=0;j<linenum;j++) {
            List<View> lineviews = mAllViews.get(j);
            int lh = mLineHeights.get(j);
            int left = 0;

            for(int k=0;k<lineviews.size();k++) {
                View child = lineviews.get(k);
                if(child.getVisibility() == View.GONE) {
                    continue;
                }

                //MarginLayoutParams lp = (MarginLayoutParams)child.getLayoutParams();
                int leftMargin = mLeftMargin;
                int rightMargin = mRightMargin;
                int topMargin = mTopMargin;

                int cl = left + leftMargin;
                int ct = top + topMargin;
                int cr = cl + child.getMeasuredWidth();
                int cb = ct + child.getMeasuredHeight();



                child.layout(cl, ct, cr, cb);

                left += child.getMeasuredWidth() + leftMargin + rightMargin;

            }

            left = 0;
            top += lh;
        }
    }
}
