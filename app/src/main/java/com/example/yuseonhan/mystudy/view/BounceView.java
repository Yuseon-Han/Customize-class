package com.example.yuseonhan.mystudy.view;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import com.example.yuseonhan.mystudy.R;

/**
 * Created by Yuseon on 2017. 3. 9..
 */

public class BounceView<T extends View> {
    final float bouncingDistance;
    private T view;

    public BounceView(T view) {
        this.view = view;
        bouncingDistance = view.getContext().getApplicationContext().getResources().getDimensionPixelSize(R.dimen.bouncing_distance);
    }

    public void startBouncing() {
        startBouncing(0);
    }

    public void startBouncing(long delay) {
        float subDistance = bouncingDistance / 2.5f;

        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(.05f, -bouncingDistance);
        kf1.setInterpolator(new OvershootInterpolator());
        Keyframe kf2 = Keyframe.ofFloat(.1f, 0f);
        Keyframe kf3 = Keyframe.ofFloat(.25f, -subDistance);
        kf3.setInterpolator(new DecelerateInterpolator());
        Keyframe kf4 = Keyframe.ofFloat(.4f, 0f);
        Keyframe kf5 = Keyframe.ofFloat(.55f, -subDistance / 3);
        Keyframe kf6 = Keyframe.ofFloat(.7f, 0f);
        Keyframe kf7 = Keyframe.ofFloat(.85f, -subDistance / 5);
        Keyframe kf8 = Keyframe.ofFloat(1f, 0f);

        PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe("translationY", kf0, kf1, kf2, kf3, kf4, kf5, kf6, kf7, kf8);
        ObjectAnimator oa = ObjectAnimator.ofPropertyValuesHolder(view, pvhRotation);
        oa.setDuration(1500);
        oa.setStartDelay(delay);
        oa.start();
    }
}
