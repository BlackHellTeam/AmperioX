package com.bosphorusinteractive;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by livinglab on 5.09.2016.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        registerBatteryLevelReceiver();
    }
    //Register Battery receiver with intent filter
    private void registerBatteryLevelReceiver() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Battery_Receiver rec = new Battery_Receiver();
        registerReceiver(rec, filter);


    }


}
