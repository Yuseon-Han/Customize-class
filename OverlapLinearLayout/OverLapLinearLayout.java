package com.example.yuseon.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * This is a simple linear layout can overlap it's children.
 * It has many unsupported factors, but it's easy to customize.
 * <p>
 * Only Horizontal. (Not support yet)
 * ChildView shouldn't have margin or padding (Not support yet,, and many things)
 * Created by Yuseon on 2017. 3. 9..
 */

public class OverLapLinearLayout extends LinearLayout {
    private int overlapWidth = 0;

    public OverLapLinearLayout(Context context) {
        super(context);
    }

    public OverLapLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttr(context, attrs);
    }

    public OverLapLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttr(context, attrs);
    }

    private void setAttr(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.OverLapLinearLayout,
                0, 0);
        try {
            overlapWidth = (int) a.getDimension(R.styleable.OverLapLinearLayout_overlapWidth, 0);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
        int maxHeight = MeasureSpec.getSize(heightMeasureSpec);

        int totalChildWidth = 0;
        int highestChildHeight = 0;
        final int count = getChildCount();

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            final LayoutParams lp = (LayoutParams) child.getLayoutParams();

            child.measure(MeasureSpec.makeMeasureSpec(lp.width, MeasureSpec.EXACTLY)
                    , MeasureSpec.makeMeasureSpec(lp.height, MeasureSpec.EXACTLY));

            highestChildHeight = Math.max(highestChildHeight, lp.height);

            totalChildWidth += lp.width;
        }


        int width = maxWidth, height = maxHeight;
        switch (MeasureSpec.getMode(widthMeasureSpec)) {
            case MeasureSpec.AT_MOST:
                width = getPaddingLeft() + getPaddingRight() + totalChildWidth - (overlapWidth * (count - 1));
                break;
        }

        switch (MeasureSpec.getMode(heightMeasureSpec)) {
            case MeasureSpec.AT_MOST:
                height = getPaddingTop() + getPaddingBottom() + highestChildHeight;
                break;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();

        int maxHeight = getMeasuredHeight();

        int childLeft = getPaddingLeft();

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            int left = childLeft;
            int top = (maxHeight - child.getMeasuredHeight()) / 2;
            child.layout(left, top, left + child.getMeasuredWidth(), top + child.getMeasuredHeight());

            child.setZ(-i);

            childLeft += child.getMeasuredWidth() - overlapWidth;
        }
    }
}
