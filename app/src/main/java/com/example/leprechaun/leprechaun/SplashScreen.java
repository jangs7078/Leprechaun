package com.example.leprechaun.leprechaun;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.util.logging.Handler;
import android.content.Intent;
import android.content.Context;


public class SplashScreen extends ActionBarActivity {

    private static int SPLASH_TIME_OUT = 3000;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        context = (Context) this;
        Thread launcherThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(SPLASH_TIME_OUT);
                    Intent i = new Intent(context, MainActivity.class);
                    startActivity(i);
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        launcherThread.start();

    }

}
