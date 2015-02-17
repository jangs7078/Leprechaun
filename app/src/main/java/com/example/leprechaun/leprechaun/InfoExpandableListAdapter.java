package com.example.leprechaun.leprechaun;

import android.widget.BaseExpandableListAdapter;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by kisukjang on 2/11/15.
 */
public class InfoExpandableListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, Announcement> _listDataChild;

    public InfoExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, Announcement> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition));
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final Announcement this_week = (Announcement) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.info_list_item, null);
        }

        /* Set Announcement content */
        TextView winner_ticket_text = (TextView) convertView
                .findViewById(R.id.winner_ticket);

        winner_ticket_text.setText(this_week.winner_ticket);

        TextView rank1_name_text = (TextView) convertView
                .findViewById(R.id.rank1_name);
        TextView rank1_ticket_num_text = (TextView) convertView
                .findViewById(R.id.rank1_ticket_num);
        rank1_name_text.setText(this_week.rank_list.get(0).get(0));
        rank1_ticket_num_text.setText(this_week.rank_list.get(0).get(1));

        TextView rank2_name_text = (TextView) convertView
                .findViewById(R.id.rank2_name);
        TextView rank2_ticket_num_text = (TextView) convertView
                .findViewById(R.id.rank2_ticket_num);
        rank2_name_text.setText(this_week.rank_list.get(1).get(0));
        rank2_ticket_num_text.setText(this_week.rank_list.get(1).get(1));

        TextView rank3_name_text = (TextView) convertView
                .findViewById(R.id.rank3_name);
        TextView rank3_ticket_num_text = (TextView) convertView
                .findViewById(R.id.rank3_ticket_num);
        rank3_name_text.setText(this_week.rank_list.get(2).get(0));
        rank3_ticket_num_text.setText(this_week.rank_list.get(2).get(1));
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
        //return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.info_list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
