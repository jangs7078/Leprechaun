package com.example.leprechaun.leprechaun;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by kisukjang on 1/25/15.
 */
public class FragmentSetting extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_setting, container, false);

        TextView lockscreenTextView = (TextView) rootView.findViewById(R.id.lockscreen_label);
        lockscreenTextView.setText("Lock Screen");
        return rootView;
    }
}
