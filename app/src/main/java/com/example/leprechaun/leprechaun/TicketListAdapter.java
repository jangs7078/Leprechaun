package com.example.leprechaun.leprechaun;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by kisukjang on 2/27/15.
 */
public class TicketListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<JSONObject> items;

    public TicketListAdapter(Activity activity, List<JSONObject> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.ticket_list_item, null);


        TextView ticket_tv = (TextView) convertView.findViewById(R.id.ticket);
        JSONObject item = items.get(position);
        try {
            ticket_tv.setText(item.get("ticket").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
