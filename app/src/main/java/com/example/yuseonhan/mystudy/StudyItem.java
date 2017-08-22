package com.example.yuseonhan.mystudy;

import android.support.v4.app.Fragment;

import com.example.yuseonhan.mystudy.bouncer.FragmentBounce;

/**
 * Created by YuseonHan on 2017. 8. 22..
 */

public enum StudyItem {
    BOUNCE_OVERLAP(R.string.item_bounce) {
        @Override
        protected Fragment getFragment() {
            return FragmentBounce.getFragment();
        }
    };

    private int titleId;

    StudyItem(int titleId) {
        this.titleId = titleId;
    }

    public int getTitleId() {
        return titleId;
    }

    protected abstract Fragment getFragment();
}
