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
public class AdListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<JSONObject> items;

    public AdListAdapter(Activity activity, List<JSONObject> items) {
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
            convertView = inflater.inflate(R.layout.ad_list_item, null);


        TextView store_tv = (TextView) convertView.findViewById(R.id.store);
        TextView watched_num_tv = (TextView) convertView.findViewById(R.id.watched_num);
        JSONObject item = items.get(position);

        try {
            store_tv.setText(item.get("store").toString());
            watched_num_tv.setText(item.get("watched_num").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
