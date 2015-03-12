package com.example.leprechaun.leprechaun;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kisukjang on 2/27/15.
 */
public class FragmentProfileTwo extends ListFragment {
    private Context context;
    List<String> reward_list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile_two, container, false);
        context = this.getActivity();

        reward_list = new ArrayList<String>();
        reward_list.add("My Profile");
        reward_list.add("Invite Friends");

        ProfileListAdapter adapter = new ProfileListAdapter(this.getActivity(), reward_list);
        setListAdapter(adapter);

        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(context, ProfileDetailActivity.class);
        intent.putExtra(MainActivity.PROFILE_DETAIL_TYPE, reward_list.get(position));
        startActivity(intent);
    }
}
