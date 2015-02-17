package com.example.leprechaun.leprechaun;

import android.support.v4.app.FragmentPagerAdapter;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.app.Fragment;


/**
 * Created by kisukjang on 1/27/15.
 */
public class AdsAdapter extends FragmentPagerAdapter{
    Context ctxt=null;

    public AdsAdapter(Context ctxt, FragmentManager mgr) {
        super(mgr);
        this.ctxt=ctxt;
    }

    @Override
    public int getCount() {
        return(100000);
    }

    @Override
    public SingleAdFragment getItem(int position) {
        LeprechaunApp app = (LeprechaunApp) ctxt;
        app.ticket_list.add(app.getRandomNumberAsString());
        return(SingleAdFragment.newInstance(position));
    }

    @Override
    public String getPageTitle(int position) {
        return(SingleAdFragment.getTitle(ctxt, position));
    }
}
