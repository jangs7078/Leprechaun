package com.example.leprechaun.leprechaun;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by kisukjang on 2/11/15.
 */
public class Announcement {
    String winner_ticket;
    ArrayList<ArrayList<String>> rank_list;

    public Announcement(String winner_ticket, ArrayList<ArrayList<String>> rank_list) {
        super();
        this.winner_ticket = winner_ticket;
        this.rank_list = rank_list;
    }
}
