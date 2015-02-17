package com.example.leprechaun.leprechaun;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;
import android.widget.LinearLayout;
import android.graphics.drawable.Drawable;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.support.v4.view.ViewPager;
import android.support.v4.view.PagerAdapter;
import java.util.ArrayList;

/**
 * Created by kisukjang on 1/25/15.
 */
public class FragmentAd extends Fragment{

    private ViewFlipper viewFlipper;
    private float lastX;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_ad, container, false);

        ViewPager pager=(ViewPager)rootView.findViewById(R.id.pager);

        pager.setAdapter(buildAdapter());


        return rootView;
    }

    private PagerAdapter buildAdapter() {
        return(new AdsAdapter(getActivity().getApplicationContext(), getChildFragmentManager()));
    }

}
