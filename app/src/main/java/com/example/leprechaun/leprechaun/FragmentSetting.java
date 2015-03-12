package com.example.leprechaun.leprechaun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by kisukjang on 1/25/15.
 */
public class FragmentSetting extends Fragment{
    private Switch lock_screen_switch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_setting, container, false);

        final TextView lockscreenTextView = (TextView) rootView.findViewById(R.id.lockscreen_label);
        lockscreenTextView.setText("Lock Screen");

        lock_screen_switch = (Switch) rootView.findViewById(R.id.lock_screen_switch);

        final Activity parent_activity = this.getActivity();

        //set the switch to ON
        final LeprechaunApp app = (LeprechaunApp) parent_activity.getApplicationContext();
        lock_screen_switch.setChecked(app.lock_screen_option);

        //attach a listener to check for changes in state
        lock_screen_switch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    app.lock_screen_option = true;
                    parent_activity.startService(new Intent(parent_activity, LockScreenService.class));
                    //Intent intent = new Intent(parent_activity, LockScreenActivity.class);
                    //startActivity(intent);
                }else{
                    app.lock_screen_option = false;
                    parent_activity.stopService(new Intent(parent_activity, LockScreenService.class));
                }

            }
        });
        return rootView;
    }
}
