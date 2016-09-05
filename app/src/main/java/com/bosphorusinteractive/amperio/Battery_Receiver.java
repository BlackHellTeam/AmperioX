package com.bosphorusinteractive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by SONU on 23/05/16.
 */
public class Battery_Receiver extends BroadcastReceiver {

    boolean isCharging = false;
    @Override
    public void onReceive(Context context, Intent intent) {

        //Get intent data of battery details
        boolean isPresent = intent.getBooleanExtra("present", false);
        String technology = intent.getStringExtra("technology");
        int plugged = intent.getIntExtra("plugged", -1);
        int scale = intent.getIntExtra("scale", -1);
        int health = intent.getIntExtra("health", 0);
        int status = intent.getIntExtra("status", 0);
        int rawlevel = intent.getIntExtra("level", -1);
        int voltage = intent.getIntExtra("voltage", 0);
        int temperature = intent.getIntExtra("temperature", 0);
        int level = 0;

        Intent newIntent = new Intent("get-battery-details");

        Bundle bundle = intent.getExtras();
        Log.i("BatteryLevel", bundle.toString());
        if (isPresent) {
            if (rawlevel >= 0 && scale > 0) {
                level = (rawlevel * 100) / scale;
            }

            //Concatenate all details of battery
            newIntent.putExtra("technology",technology);
            newIntent.putExtra("plugged",getPlugTypeString(plugged));
            newIntent.putExtra("health",getHealthString(health));

            newIntent.putExtra("voltage",voltage);
            newIntent.putExtra("temperature",temperature);
            newIntent.putExtra("level",level);


            newIntent.putExtra("status",getStatusString(status));
            if(bundle.getInt("current_now")==0) {

                newIntent.putExtra("Current",String.valueOf(bundle.getInt("current_now")));
                Log.d("current_0",String.valueOf(bundle.getInt("current_now")));

            }else {
                newIntent.putExtra("Current", String.valueOf(bundle.getInt("current_now")));



                Log.d("current_1", String.valueOf(bundle.getInt("current_now")));
            }
        }
            sendData(context,newIntent);

    }

    //Get Plug type for battery
    private String getPlugTypeString(int plugged) {
        String plugType = "Unknown";
        switch (plugged) {
            case BatteryManager.BATTERY_PLUGGED_AC:
                plugType = "AC";
                break;
            case BatteryManager.BATTERY_PLUGGED_USB:
                plugType = "USB";
                break;
            case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                plugType = "WIRELESS";
                break;
            default:
                plugType = "Discharging";
                break;
        }
        return plugType;
    }

    //Get Health of battery
    private String getHealthString(int health) {
        String healthString = "Unknown";

        switch (health) {
            case BatteryManager.BATTERY_HEALTH_DEAD:
                healthString = "Dead";
                break;
            case BatteryManager.BATTERY_HEALTH_GOOD:
                healthString = "Good";
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                healthString = "Over Voltage";
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                healthString = "Over Heat";
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                healthString = "Failure";
                break;
            case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                healthString = "Failure";
                break;
        }

        return healthString;
    }

    //Get Status of battery
    private String getStatusString(int status) {
        String statusString = "";

        switch (status) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                statusString = "Charging";
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                statusString = "Discharging";
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                statusString = "Full";
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                statusString = "Not Charging";
                break;
        }

        return statusString;
    }

    private void sendData(Context context,Intent intent){



        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
