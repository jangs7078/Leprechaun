package com.example.leprechaun.leprechaun;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kisukjang on 1/25/15.
 */
public class FragmentProfile extends Fragment{
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        // get the listview
        expListView = (ExpandableListView) rootView.findViewById(R.id.ticket_list_view);

        // preparing list data
        prepareListData();

        listAdapter = new TicketExpandableListAdapter(getActivity().getApplicationContext(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        return rootView;
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Tickets");

        LeprechaunApp app = (LeprechaunApp) getActivity().getApplicationContext();
        // Adding child data
        List<String> ticket_list = new ArrayList<String>();
        for(int i = 0; i < app.ticket_list.size(); i++){
            ticket_list.add(app.ticket_list.get(i));
        }

        listDataChild.put(listDataHeader.get(0), ticket_list);
    }
}
