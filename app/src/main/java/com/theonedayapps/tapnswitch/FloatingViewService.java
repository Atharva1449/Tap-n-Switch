package com.theonedayapps.tapnswitch;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import com.theonedayapps.tapnswitch.MainActivity;
import androidx.core.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

import static android.app.AlarmManager.ELAPSED_REALTIME;
import static android.os.SystemClock.elapsedRealtime;
import static com.theonedayapps.tapnswitch.MainActivity.tobeornottobe;
import static com.theonedayapps.tapnswitch.MainActivity.what;

public class FloatingViewService extends Service implements View.OnClickListener {

private int lastaction;
    private WindowManager mWindowManager;
    private View mFloatingView;
    private View collapsedView;
    private View expandedView;
   private Button button;
   private boolean qq=false;
   public int counter=0;
     byte what1=what;
     Intent launchIntent;
    public FloatingViewService() {
    }
////

    ////
    @Override
    public IBinder onBind(Intent intent) {
      //  throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        //getting the widget layout from xml using layout inflater
        mFloatingView = LayoutInflater.from(this).inflate(R.layout.layout_floating_widget, null);
       // button=R.id.collapsed_iv
        //setting the layout parameters
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY : WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);


        //getting windows services and adding the floating view to it
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(mFloatingView, params);


        //getting the collapsed and expanded view from the floating view
        collapsedView = mFloatingView.findViewById(R.id.layoutCollapsed);
        expandedView = mFloatingView.findViewById(R.id.layoutExpanded);

        //adding click listener to close button and expanded view
       // mFloatingView.findViewById(R.id.buttonClose).setOnClickListener(this);

        //mFloatingView.findViewById(R.id.collapsed123).setOnClickListener(this);
        expandedView.setOnClickListener(this);

        //adding an touchlistener to make drag movement of the floating widget
        mFloatingView.findViewById(R.id.relativeLayoutParent).setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                         lastaction = MotionEvent.ACTION_DOWN;
                        initialX = params.x;
                        initialY = params.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;

                    case MotionEvent.ACTION_UP:
//                            int a=0;
//                        //when the drag is ended switching the state of the widget
//                        if(lastaction==MotionEvent.ACTION_DOWN){
//                        while(a==0){
//
//                          //  mFloatingView.findViewById(R.id.collapsed_iv).setOnClickListener(this);
//                            Toast.makeText(FloatingViewService.this, "Clicked!", Toast.LENGTH_SHORT).show();
//                            a=1;
//                        }
                        //}
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        lastaction = MotionEvent.ACTION_MOVE;
                        //this code is helping the widget to move around the screen with fingers
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);
                        mWindowManager.updateViewLayout(mFloatingView, params);
                        return true;
                }
                return false;
            }
        });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.layoutCollapsed:
//                //if(lastaction==MotionEvent.ACTION_DOWN){
//                    Toast.makeText(FloatingViewService.this, "Clicked!", Toast.LENGTH_SHORT).show();
//                    collapsedView.setVisibility(View.VISIBLE);
//                    expandedView.setVisibility(View.GONE);
                //}
                //switching views
//                collapsedView.setVisibility(View.VISIBLE);
//                expandedView.setVisibility(View.GONE);
//                break;
//            case R.id.collapsed_iv:
//                 qq =true;
//                Toast.makeText(FloatingViewService.this, "Clicked"+qq, Toast.LENGTH_SHORT).show();
//               // openApp(this, "com.google.android.youtube");


                //break;
//            case R.id.buttonClose:
//                //closing the widget
//                Toast.makeText(FloatingViewService.this, "Closed!", Toast.LENGTH_SHORT).show();
//
//
//                stopSelf();
//                break;
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        startTimer();
        onTaskRemoved(intent);
      //  Toast.makeText(getApplicationContext(),""+qq,
              //  Toast.LENGTH_SHORT).show();


//////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        mFloatingView.findViewById(R.id.collapsed_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // qq=true;
                //if(qq==true){
                    //Toast.makeText(FloatingViewService.this, "-", Toast.LENGTH_SHORT).show();
                    //Intent act=new Intent(FloatingViewService.this,MainActivity.class);
                  //  PendingIntent pendingIntent= PendingIntent.getActivity(FloatingViewService.this,0,act,0);

                    switch(what1) {
                        case 1:
                            launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
                                startActivity(launchIntent);

                            break;

                        case 2:
                            launchIntent = getPackageManager().getLaunchIntentForPackage("com.android.chrome");
                                startActivity(launchIntent);

                            break;

                        case 3:
                            launchIntent = getPackageManager().getLaunchIntentForPackage("cn.wps.moffice_eng");
                                startActivity(launchIntent);

                            break;
                    }

                //}
            }
        });
   // MainActivity obj=new MainActivity();
//System.out.println(obj.what);
        ///


            //qq=false;
        //String abc;
        //Notificint what1=what;ation notification;
       // startForeground(1,notification);
        ///
        return START_STICKY;
    }


/////////////////////////
private Timer timer;
    private TimerTask timerTask;
    public void startTimer() {
        timer = new Timer();
        timerTask = new TimerTask() {
            public void run() {
                Log.i("Count", "=========  "+ (counter++));
            }
        };
        timer.schedule(timerTask, 1000, 1000); //
    }

    public void stoptimertask() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    ///////////////////

        @Override
        public void onTaskRemoved (Intent rootIntent){
        if(tobeornottobe==false) {
            startTimer();
            Intent restartServiceIntent = new Intent(getApplicationContext(), this.getClass());

            PendingIntent restartServicePendingIntent = PendingIntent.getService(
                    getApplicationContext(), 1, restartServiceIntent, PendingIntent.FLAG_ONE_SHOT);
            AlarmManager alarmService = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmService.set(ELAPSED_REALTIME, elapsedRealtime() + 1000,
                    restartServicePendingIntent);

            super.onTaskRemoved(rootIntent);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //disconnect();
        if (mFloatingView != null) mWindowManager.removeView(mFloatingView);
        stoptimertask();

        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("restartservice");
        broadcastIntent.setClass(this, MainActivity.class);
        this.sendBroadcast(broadcastIntent);
    }
}
