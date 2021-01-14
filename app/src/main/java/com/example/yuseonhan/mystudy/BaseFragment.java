package com.example.yuseonhan.mystudy;

import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.Unbinder;

/**
 * Created by YuseonHan on 2017. 8. 21..
 */

public class BaseFragment extends Fragment {
    protected Unbinder unbinder;
    protected View fragmentView;

    // test comment

    @Override
    public void onDestroyView() {
        fragmentView = null;
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }
}
