package com.example.leprechaun.leprechaun;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kisukjang on 2/27/15.
 */
public class ProfileListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<String> items;

    public ProfileListAdapter(Activity activity, List<String> items) {
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
            convertView = inflater.inflate(R.layout.profile_list_item, null);


        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setText(items.get(position));
        
        return convertView;
    }
}
