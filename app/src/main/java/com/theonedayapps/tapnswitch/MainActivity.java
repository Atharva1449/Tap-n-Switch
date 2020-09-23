package com.theonedayapps.tapnswitch;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity  {
    private static final int SYSTEM_ALERT_WINDOW_PERMISSION = 2084;
    private RadioGroup radiogrp;
    private RadioButton radiobbtn;
    private Button startbutton;
    private Button stopbutton;
    public static int what;
    public static boolean tobeornottobe=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            askPermission();
        }
        stopbutton=findViewById(R.id.buttonstop);

       startbutton= findViewById(R.id.buttonCreateWidget);
       startbutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               tobeornottobe=false;
               if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                   startService(new Intent(MainActivity.this, FloatingViewService.class));
                   finish();
               } else if (Settings.canDrawOverlays(MainActivity.this)) {
                   startService(new Intent(MainActivity.this, FloatingViewService.class));
                   finish();
               } else {
                   askPermission();
                  // Toast.makeText(this, "You need System Alert Window Permission to do this", Toast.LENGTH_SHORT).show();
               }
           }
       });
        radiogrp=(RadioGroup)findViewById(R.id.radiogroupid);
        radiogrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radiobbtn=(RadioButton)findViewById(checkedId);
                switch(checkedId){
                    case R.id.radiobuttonyt: {
                        what=1;
                    }
                    break;
                    case R.id.radiobuttonch: {
                        what=2;
                    }
                    break;
                    case R.id.radiobuttonpdf: {
                        what=3;
                    }
                    break;
                } }
        });

        stopbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tobeornottobe=true;
                Intent serviceIntent = new Intent(MainActivity.this, FloatingViewService.class);
                stopService(serviceIntent);
                //System.out.println("aaaa");
            }
        });

    }


    private void askPermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, SYSTEM_ALERT_WINDOW_PERMISSION);
    }

//    @Override
//    public void onClick(View v) {
//
//    }
}
 