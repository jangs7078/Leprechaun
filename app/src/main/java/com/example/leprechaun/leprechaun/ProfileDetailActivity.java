package com.example.leprechaun.leprechaun;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kisukjang on 2/28/15.
 */
public class ProfileDetailActivity extends Activity {
    Button logout_btn;

    private Context context;
    private List<JSONObject> detail_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);
        ListView detail_lv = (ListView) findViewById(R.id.detail_lv);
        TextView title_tv = (TextView) findViewById(R.id.title);
        ImageView profile_iv = (ImageView) findViewById(R.id.profile_image);
        profile_iv.setVisibility(View.GONE);
        logout_btn = (Button) findViewById(R.id.logout_btn);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent intent = new Intent(context, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                ((Activity) context).finish();
            }
        });
        logout_btn.setVisibility(View.GONE);

        context = (Context) this;
        detail_list = new ArrayList<JSONObject>();

        String type = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            type = extras.getString(MainActivity.PROFILE_DETAIL_TYPE);
        }
        if(type.equals("Rewards")){
            title_tv.setText(type);
            JSONObject reward_obj;
            try {
                reward_obj = new JSONObject();
                reward_obj.put("store", "Starbucks");
                reward_obj.put("amount", "$20");
                reward_obj.put("date", "02/27/15");
                detail_list.add(reward_obj);
                reward_obj = new JSONObject();
                reward_obj.put("store", "COHO");
                reward_obj.put("amount", "$4");
                reward_obj.put("date", "02/23/15");
                detail_list.add(reward_obj);
                reward_obj = new JSONObject();
                reward_obj.put("store", "Bytes");
                reward_obj.put("amount", "$5");
                reward_obj.put("date", "02/20/15");
                detail_list.add(reward_obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RewardListAdapter adapter = new RewardListAdapter(this, detail_list);
            detail_lv.setAdapter(adapter);
        } else if (type.equals("Tickets")){
            title_tv.setText(type);
            JSONObject ticket_obj;
            try {
                LeprechaunApp app = (LeprechaunApp) context.getApplicationContext();
                for (int i = 0; i < app.ticket_list.size(); i++) {
                    ticket_obj = new JSONObject();
                    ticket_obj.put("ticket", "#"+app.ticket_list.get(i));
                    detail_list.add(ticket_obj);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            TicketListAdapter adapter = new TicketListAdapter(this, detail_list);
            detail_lv.setAdapter(adapter);
        } else if (type.equals("Ads Watched")){
            title_tv.setText(type);
            JSONObject ad_obj;
            try {
                ad_obj = new JSONObject();
                ad_obj.put("store", "Bytes");
                ad_obj.put("watched_num", "13");
                detail_list.add(ad_obj);
                ad_obj = new JSONObject();
                ad_obj.put("store", "Nike");
                ad_obj.put("watched_num", "11");
                detail_list.add(ad_obj);
                ad_obj = new JSONObject();
                ad_obj.put("store", "Starbucks");
                ad_obj.put("watched_num", "6");
                detail_list.add(ad_obj);
                ad_obj = new JSONObject();
                ad_obj.put("store", "COHO");
                ad_obj.put("watched_num", "5");
                detail_list.add(ad_obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            AdListAdapter adapter = new AdListAdapter(this, detail_list);
            detail_lv.setAdapter(adapter);
        } else if (type.equals("My Profile")) {
            profile_iv.setVisibility(View.VISIBLE);
            logout_btn.setVisibility(View.VISIBLE);
            JSONObject ad_obj;
            try {
                ad_obj = new JSONObject();
                ad_obj.put("store", "Name");
                ad_obj.put("watched_num", "Eesley Chuck");
                detail_list.add(ad_obj);
                ad_obj = new JSONObject();
                ad_obj.put("store", "ID");
                ad_obj.put("watched_num", "e145@stanford.edu");
                detail_list.add(ad_obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            AdListAdapter adapter = new AdListAdapter(this, detail_list);
            detail_lv.setAdapter(adapter);
        } else {
            View lv_top_line = (View) findViewById(R.id.lv_top_line);
            View lv_bottom_line = (View) findViewById(R.id.lv_bottom_line);
            RelativeLayout parent_rl = (RelativeLayout) lv_top_line.getParent();
            parent_rl.removeView(lv_top_line);
            parent_rl.removeView(lv_bottom_line);

            title_tv.setText("Coming Soon...");
        }
    }
}
