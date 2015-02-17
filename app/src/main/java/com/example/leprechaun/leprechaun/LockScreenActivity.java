package com.example.leprechaun.leprechaun;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.WindowManager;
import android.app.KeyguardManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.app.Activity;
import java.util.Random;
import android.view.Window;
import android.widget.Button;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.content.Context;

public class LockScreenActivity extends Activity{
    public final static String LOCK_SCREEN_OFF = "com.example.leprechaun.LOCK_SCREEN_OFF";

    KeyguardManager.KeyguardLock k1;
    boolean inDragMode;
    int selectedImageViewX;
    int selectedImageViewY;
    int windowwidth;
    int windowheight;
    ImageView phone,lock_screen_ad, background_image;
    //int phone_x,phone_y;
    int home_x,home_y;
    int[] droidpos;

    private int _xDelta;
    private int _yDelta;

    private Activity curr_activity;

    private LayoutParams layoutParams;

    @Override
    public void onAttachedToWindow() {
        // TODO Auto-generated method stub
        //this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG|WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onAttachedToWindow();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_lock_screen);

        background_image = (ImageView) findViewById(R.id.background);
        background_image.setScaleType(ImageView.ScaleType.FIT_XY);
        background_image.setImageResource(R.drawable.background);

        lock_screen_ad = (ImageView) findViewById(R.id.lock_screen_ad);
        lock_screen_ad.setScaleType(ImageView.ScaleType.FIT_XY);

        Random r = new Random();
        int random_num = r.nextInt(4);

        LeprechaunApp app = (LeprechaunApp) getApplicationContext();
        lock_screen_ad.setImageResource(app.ads_list.get(random_num));
        lock_screen_ad.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                onSlideTouch(v, event);
                return false;
            }
        });

        if(getIntent()!=null&&getIntent().hasExtra("kill")&&getIntent().getExtras().getInt("kill")==1){
            // Toast.makeText(this, "" + "kill activityy", Toast.LENGTH_SHORT).show();
            finish();
        }

        try{

  /*      KeyguardManager km =(KeyguardManager)getSystemService(KEYGUARD_SERVICE);
        k1 = km.newKeyguardLock("IN");
        k1.disableKeyguard();*/
            StateListener phoneStateListener = new StateListener();
            TelephonyManager telephonyManager =(TelephonyManager)getSystemService(TELEPHONY_SERVICE);
            telephonyManager.listen(phoneStateListener,PhoneStateListener.LISTEN_CALL_STATE);

            windowwidth=getWindowManager().getDefaultDisplay().getWidth();
            System.out.println("windowwidth"+windowwidth);
            windowheight=getWindowManager().getDefaultDisplay().getHeight();
            System.out.println("windowheight"+windowheight);

        /* phone =(ImageView)findViewById(R.id.phone);
        MarginLayoutParams marginParams = new MarginLayoutParams(phone.getLayoutParams());
         marginParams.setMargins(0,windowheight/32,windowwidth/24,0);
         LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(marginParams);
         phone.setLayoutParams(layoutParams1);
*/


            final LeprechaunApp lpc_app = app;


            //PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);

            // PowerManager.WakeLock w1 =pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP|PowerManager.FULL_WAKE_LOCK,"MyApp");
            // w1.acquire();
            // w1.release();
        }catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void onSlideTouch( View view, MotionEvent event )
    {
        //When the user pushes down on an ImageView
        if ( event.getAction() == MotionEvent.ACTION_DOWN )
        {
            inDragMode = true; //Set a variable so we know we started draggin the imageView
            //Set the selected ImageView X and Y exact position
            selectedImageViewX = Math.abs((int)event.getRawX()-((ImageView)view).getLeft());
            selectedImageViewY = Math.abs((int)event.getRawY()-((ImageView)view).getTop());
            //Bring the imageView in front
            ((ImageView)view).bringToFront();
        }

        //When the user let's the ImageView go (raises finger)
        if ( event.getAction() == MotionEvent.ACTION_UP )
        {
            inDragMode = false; //Reset the variable which let's us know we're not in drag mode anymore
        }

        //When the user keeps his finger on te screen and drags it (slides it)
        if ( event.getAction() == MotionEvent.ACTION_MOVE )
        {
            //If we've started draggin the imageView
            if ( inDragMode )
            {
                //Get a parameters object (THIS EXAMPLE IS FOR A RELATIVE LAYOUT)
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)lock_screen_ad.getLayoutParams();
                //Change the position of the imageview accordingly
                params.setMargins((int)event.getRawX()-selectedImageViewX, (int)event.getRawY()-selectedImageViewY, 0, 0);
                //Set the new params
                lock_screen_ad.setLayoutParams(params);

                //If we hit a limit with our imageView position
                if( (int)event.getRawX()-selectedImageViewX > 20)
                {
                    finish();
                }
            }
        }

    }

    class StateListener extends PhoneStateListener{
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            super.onCallStateChanged(state, incomingNumber);
            switch(state){
                case TelephonyManager.CALL_STATE_RINGING:
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    System.out.println("call Activity off hook");
                    finish();



                    break;
                case TelephonyManager.CALL_STATE_IDLE:
                    break;
            }
        }
    };

    @Override
    public void onBackPressed() {
        // Don't allow back to dismiss.
        return;
    }

    //only used in lockdown mode
    @Override
    protected void onPause() {
        super.onPause();

        // Don't hang around.
        // finish();
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Don't hang around.
        // finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)||(keyCode == KeyEvent.KEYCODE_POWER)||(keyCode == KeyEvent.KEYCODE_VOLUME_UP)||(keyCode == KeyEvent.KEYCODE_CAMERA)) {
            //this is where I can do my stuff
            return true; //because I handled the event
        }
        if((keyCode == KeyEvent.KEYCODE_HOME)){

            return true;
        }

        return false;

    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_POWER ||(event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN)||(event.getKeyCode() == KeyEvent.KEYCODE_POWER)) {
            //Intent i = new Intent(this, NewActivity.class);
            //startActivity(i);
            return false;
        }
        if((event.getKeyCode() == KeyEvent.KEYCODE_HOME)){

            System.out.println("alokkkkkkkkkkkkkkkkk");
            return true;
        }
        return false;
    }

    private BroadcastReceiver lockScreenOffReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Extract data included in the Intent
            stopService(new Intent(curr_activity, LockScreenService.class));
        }
    };

    /*public void unloack(){
          finish();
    }*/
    public void onDestroy(){
        // k1.reenableKeyguard();
        super.onDestroy();
    }
}
