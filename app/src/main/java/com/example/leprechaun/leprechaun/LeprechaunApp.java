package com.example.leprechaun.leprechaun;

import android.app.Application;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
/**
 * Created by kisukjang on 1/27/15.
 */
public class LeprechaunApp extends Application {
    private double rangeMin = 10000000;
    private double rangeMax = 99999999;

    public boolean lock_screen_option;
    public ArrayList<Integer> ads_list = new ArrayList<>();
    public ArrayList<String> ticket_list = new ArrayList<>();

    public String getRandomNumberAsString(){
        Random r = new Random();
        double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
        randomValue = Math.round(randomValue);
        return String.format("%.0f", randomValue);
    }
}
