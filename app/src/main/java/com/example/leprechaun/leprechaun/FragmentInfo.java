package com.example.leprechaun.leprechaun;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
/**
 * Created by kisukjang on 2/11/15.
 */
public class FragmentInfo extends Fragment{
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, Announcement> listDataChild;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_info, container, false);

        // get the listview
        expListView = (ExpandableListView) rootView.findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new InfoExpandableListAdapter(getActivity().getApplicationContext(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        return rootView;
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, Announcement>();

        // Adding child data
        listDataHeader.add("Last Week");
        listDataHeader.add("2 Weeks Ago");
        listDataHeader.add("3 Weeks Ago");

        ArrayList<String> temp;
        ArrayList<ArrayList<String>> rank;
        // Adding child data
        rank = new ArrayList<>();
        temp = new ArrayList<>();
        temp.add("Maria");
        temp.add("142");
        rank.add(temp);
        temp = new ArrayList<>();
        temp.add("Do");
        temp.add("102");
        rank.add(temp);
        temp = new ArrayList<>();
        temp.add("Mook");
        temp.add("47");
        rank.add(temp);
        Announcement last_week = new Announcement("579221", rank);
        listDataChild.put(listDataHeader.get(0), last_week);

        rank = new ArrayList<>();
        temp = new ArrayList<>();
        temp.add("Maria");
        temp.add("120");
        rank.add(temp);
        temp = new ArrayList<>();
        temp.add("Brian");
        temp.add("82");
        rank.add(temp);
        temp = new ArrayList<>();
        temp.add("Mook");
        temp.add("39");
        rank.add(temp);
        Announcement two_week = new Announcement("528221", rank);
        listDataChild.put(listDataHeader.get(1), two_week);

        rank = new ArrayList<>();
        temp = new ArrayList<>();
        temp.add("Mook");
        temp.add("58");
        rank.add(temp);
        temp = new ArrayList<>();
        temp.add("Maria");
        temp.add("22");
        rank.add(temp);
        temp = new ArrayList<>();
        temp.add("Brain");
        temp.add("17");
        rank.add(temp);
        Announcement three_week = new Announcement("19221", rank);
        listDataChild.put(listDataHeader.get(2), three_week);
    }
}
