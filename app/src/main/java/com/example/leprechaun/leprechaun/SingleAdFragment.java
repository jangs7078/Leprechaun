package com.example.leprechaun.leprechaun;

import android.media.Image;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.content.Context;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

/**
 * Created by kisukjang on 1/27/15.
 */
public class SingleAdFragment extends Fragment{
    private static final String KEY_POSITION="position";

    static SingleAdFragment newInstance(int position) {
        SingleAdFragment frag=new SingleAdFragment();
        Bundle args=new Bundle();

        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }

    static String getTitle(Context ctxt, int position) {
        return(String.format(ctxt.getString(R.string.hint), position + 1));
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.single_ad, container, false);
        ImageView img_view=(ImageView)result.findViewById(R.id.adView);
        img_view.setScaleType(ScaleType.FIT_XY);

        int position=getArguments().getInt(KEY_POSITION, -1);

        position = position % 4;
        LeprechaunApp app = (LeprechaunApp) this.getActivity().getApplicationContext();
        img_view.setImageResource(app.ads_list.get(position));


        return(result);
    }
}
