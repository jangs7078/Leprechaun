package com.example.leprechaun.leprechaun;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;
/**
 * Created by kisukjang on 1/25/15.
 */
public class FragmentAd extends Fragment{
    private Context ctxt;
    private ViewFlipper viewFlipper;
    private float lastX;
    private int curr_page = 99999;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_ad, container, false);

        ViewPager pager=(ViewPager)rootView.findViewById(R.id.pager);

        pager.setAdapter(buildAdapter());
        pager.setCurrentItem(curr_page);

        ctxt = getActivity().getApplicationContext();
        LeprechaunApp app = (LeprechaunApp) ctxt;
        app.ticket_list.add(app.getRandomNumberAsString());

        pager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                if(arg0 >= curr_page) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://leprechaun.launchrock.com"));
                    startActivity(browserIntent);
                } else {
                    LeprechaunApp app = (LeprechaunApp) ctxt;
                    app.ticket_list.add(app.getRandomNumberAsString());
                }
                curr_page = arg0;
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        return rootView;
    }

    private PagerAdapter buildAdapter() {
        return(new AdsAdapter(getActivity().getApplicationContext(), getChildFragmentManager()));
    }

}
