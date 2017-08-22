package com.example.yuseonhan.mystudy.bouncer;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.yuseonhan.mystudy.BaseFragment;
import com.example.yuseonhan.mystudy.R;

/**
 * Created by YuseonHan on 2017. 8. 21..
 */

public class FragmentBounce extends BaseFragment {
    private final int listCount = 10;

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.linear_layout)
    LinearLayout linearLayout;

    @BindView(R.id.linear_layout_vertical)
    LinearLayout linearLayoutVertical;

    @BindDimen(R.dimen.bouncing_item_size)
    int itemSize;

    @BindDimen(R.dimen.bouncing_distance)
    int bouncingDistance;

    private ArrayList<ViewBouncer> bounceViews = new ArrayList<>();
    private ArrayList<ViewBouncer> bounceViewsVertical = new ArrayList<>();

    private Context context;

    public static Fragment getFragment() {
        return new FragmentBounce();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_bounce, container, false);

        unbinder = ButterKnife.bind(this, fragmentView);
        context = getContext();
        initViews();

        return fragmentView;
    }

    private void initViews() {
        TypedArray drawables = context.getResources().obtainTypedArray(R.array.bounce_icons);

        for (int i = 0; i < listCount; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setImageDrawable(drawables.getDrawable(i % drawables.length()));

            linearLayout.addView(imageView);
            imageView.getLayoutParams().width = imageView.getLayoutParams().height = itemSize;

            bounceViews.add(new ViewBouncer(imageView));
        }

        for (int i = 0; i < listCount/2; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setImageDrawable(drawables.getDrawable(i % drawables.length()));

            linearLayoutVertical.addView(imageView);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)imageView.getLayoutParams();
            if (i == 0) {
                params.topMargin = (int)(bouncingDistance *1.3f);
            }
            imageView.getLayoutParams().width = imageView.getLayoutParams().height = itemSize;

            bounceViewsVertical.add(new ViewBouncer(imageView));
        }
    }

    @OnClick(R.id.button)
    void onButtonClick() {
        for (int i = 0; i < bounceViews.size(); i++) {
            bounceViews.get(i).startBouncing(i * 35);
        }

        //vertical
        for (int i = 0; i < bounceViewsVertical.size(); i++) {
            bounceViewsVertical.get(i).startBouncing(i * 35);
        }
    }
}
