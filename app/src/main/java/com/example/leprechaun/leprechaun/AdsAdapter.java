package com.example.leprechaun.leprechaun;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by kisukjang on 1/27/15.
 */
public class AdsAdapter extends FragmentPagerAdapter{
    Context ctxt=null;
    private int max_num = 100000;

    public AdsAdapter(Context ctxt, FragmentManager mgr) {
        super(mgr);
        this.ctxt=ctxt;
    }

    @Override
    public int getCount() {
        return(max_num);
    }

    @Override
    public SingleAdFragment getItem(int position) {
        return(SingleAdFragment.newInstance(position));
    }

    @Override
    public String getPageTitle(int position) {
        return(SingleAdFragment.getTitle(ctxt, position));
    }
}
