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
public class FragmentProfileOne extends ListFragment{
    private Context context;
    List<String> account_list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile_one, container, false);

        context = this.getActivity();

        account_list = new ArrayList<String>();
        account_list.add("Rewards");
        account_list.add("Tickets");
        account_list.add("Ads Watched");

        ProfileListAdapter adapter = new ProfileListAdapter(this.getActivity(), account_list);
        setListAdapter(adapter);

        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(context, ProfileDetailActivity.class);
        intent.putExtra(MainActivity.PROFILE_DETAIL_TYPE, account_list.get(position));
        startActivity(intent);
    }
}
