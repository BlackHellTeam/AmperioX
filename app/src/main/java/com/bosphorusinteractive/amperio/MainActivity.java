package com.bosphorusinteractive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.batterydetails_demo.R;

public class MainActivity extends AppCompatActivity {
    private static TextView battery_details;
    Battery_Receiver rec;
    BatteryInfo batteryInfo;
    TextView statusText;
    TextView pluggedText;
    TextView techText;
    TextView healthText;
    TextView currentValue;
    TextView tempText;
    TextView levelText;
    TextView voltageText;
    TextView deviceValue;
    TextView modelValue;
    TextView osValue;
    boolean isCharging = false;



    String status;
    String plugged;
    String tech;
    String health;
    View device;
    String model;
    String osVersion;
    int current;
    int temp;
    int level;
    int voltage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // setUpToolbar();
        voltageText = (TextView) findViewById(R.id.voltValue);

        statusText = (TextView) findViewById(R.id.statusValue);
    //    pluggedText = (TextView) findViewById(R.id.);
        techText = (TextView) findViewById(R.id.techValue);
        healthText = (TextView) findViewById(R.id.healthValue);
        currentValue = (TextView) findViewById(R.id.currentValue);
        tempText = (TextView) findViewById(R.id.tempValue);
        levelText = (TextView) findViewById(R.id.levelValue);
        deviceValue = (TextView) findViewById(R.id.deviceValue);
        modelValue = (TextView) findViewById(R.id.modelValue);
        osValue = (TextView) findViewById(R.id.osversionValue);


        registerBatteryLevelReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("get-battery-details"));



    }

    //set up toolbar
    private void setUpToolbar() {
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //   setSupportActionBar(toolbar);
    }

    //Register Battery receiver with intent filter
    private void registerBatteryLevelReceiver() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        rec = new Battery_Receiver();
        registerReceiver(rec, filter);


    }




    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            status = intent.getStringExtra("status");
            plugged = intent.getStringExtra("plugged");
            health = intent.getStringExtra("health");
            level = intent.getIntExtra("level", 0);
            voltage = intent.getIntExtra("voltage", 0);
            tech = intent.getStringExtra("technology");
            temp = intent.getIntExtra("temperature", 0);
            current = intent.getIntExtra("current_now", 0);


            if(status.equals("Charging")){

                if(!isCharging){
                    isCharging = true;
                    MainActivity.this.changeTheme();
                }



            }else {
                if(isCharging) {
                    isCharging = false;
                    MainActivity.this.changeTheme();

                }

            }



            Log.d("volt",String.valueOf(voltage));
            Log.d("status",status);
            statusText.setText(status);
            //pluggedText.setText(plugged);
            tempText.setText(String.valueOf(temp));
            healthText.setText(health);
            levelText.setText(String.valueOf(level));
            voltageText.setText(String.valueOf(voltage));
            techText.setText(tech);
            osValue.setText(Build.VERSION.RELEASE);
            modelValue.setText(Build.MODEL);
            deviceValue.setText(Build.DEVICE);
            currentValue.setText(String.valueOf(current));
          //  currentText.setText(String.valueOf(current));


        }

    };


    public void changeTheme(){

        ViewGroup v = (ViewGroup) findViewById(R.id.cordi);

        findChild(v);

    }

    public void findChild(ViewGroup vg){
        for(int i=0; i<vg.getChildCount();i++){
            View v = vg.getChildAt(i);
           if(v instanceof CardView){
               ((CardView) v).setCardBackgroundColor(getResources().getColor(isCharging? R.color.cardBlue : R.color.cardbg));
              LinearLayout ll = (LinearLayout) ((CardView) v).getChildAt(0);

              //TextView tv1 = (TextView) ll.getChildAt(0);
              TextView tv2 = (TextView) ll.getChildAt(1);
              //tv1.setTextColor(getResources().getColor(isCharging? R.color.textColorPrimary : R.color.navigationBarColor));
               tv2.setTextColor(getResources().getColor(isCharging? R.color.textColorPrimary : R.color.navigationBarColor));


           }else if(v instanceof ViewGroup){
               findChild((ViewGroup) v);
           }
        }
    }


}