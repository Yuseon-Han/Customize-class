package com.example.yuseonhan.mystudy.bouncer;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.yuseonhan.mystudy.R;

/**
 * This is a simple linear layout can overlap it's children.
 * It has many unsupported factors, but it's easy to customize.
 * Created by Yuseon on 2017. 3. 9..
 */

public class OverLapLinearLayout extends LinearLayout {
    private int overlapWidth = 0;
    private boolean firstUp;

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
            overlapWidth = (int)a.getDimension(R.styleable.OverLapLinearLayout_overlapSize, 0);
            firstUp = a.getBoolean(R.styleable.OverLapLinearLayout_firstUp, false);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int count = getChildCount();
        if (getOrientation() == LinearLayout.HORIZONTAL) {
            setMeasuredDimension(getMeasuredWidth() - (overlapWidth * (count - 1)), getMeasuredHeight());
        } else {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight() - (overlapWidth * (count - 1)));
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();

        if (getOrientation() == LinearLayout.HORIZONTAL) {

            int maxHeight = getMeasuredHeight();
            int childLeft = getPaddingLeft();

            for (int i = 0; i < count; i++) {
                final View child = getChildAt(i);
                int left = childLeft;
                int top = (maxHeight - child.getMeasuredHeight()) / 2;
                child.layout(left, top, left + child.getMeasuredWidth(), top + child.getMeasuredHeight());

                if (firstUp) {
                    child.setZ(-i * 0.1f);
                }

                childLeft += child.getMeasuredWidth() - overlapWidth;
            }
        } else {
            int maxWidth = getMeasuredWidth();
            int childTop = getPaddingTop();

            for (int i = 0; i < count; i++) {
                final View child = getChildAt(i);
                LinearLayout.LayoutParams params = (LayoutParams)child.getLayoutParams();
                int left = (maxWidth - child.getMeasuredWidth()) / 2;
                childTop += params.topMargin;

                child.layout(left, childTop, left + child.getMeasuredWidth(), childTop + child.getMeasuredHeight());

                if (firstUp) {
                    child.setZ(-i * 0.1f);
                }

                childTop += child.getMeasuredHeight() - overlapWidth;
            }
        }
    }
}
