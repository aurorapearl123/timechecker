package com.example.ian.timechecker;


import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.widget.Toast;

public class CallReceiver extends BroadcastReceiver {
    String LAUNCHER_NUMBER = "#7878*";
    private static final ComponentName LAUNCHER_COMPONENT_NAME = new ComponentName(
            "com.example.ian.timechecker", "com.example.ian.timechecker.MainActivity");

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.wtf("intent-test" , intent.getAction().toString());

        if(intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){

            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

            if(incomingNumber.equals(LAUNCHER_NUMBER)) {
                showToast(context, "call started..."+incomingNumber);
                //com.example.ian.timechecker


//                if (isLauncherIconVisible(context)) {
//
//                } else {
                    Intent i = new Intent();
                    i.setClassName("com.example.ian.timechecker", "com.example.ian.timechecker.MainActivity");
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
               // }
            }
            else {
                showToast(context, "Not equal..."+incomingNumber);
            }




        }
        else if(intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_IDLE)){
            showToast(context, "Call ended..");
        }


    }

    public void showToast(Context context, String message) {
        Toast toast = Toast.makeText(context, message , Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private boolean isLauncherIconVisible(Context context) {
        int enabledSetting = context.getPackageManager().getComponentEnabledSetting(LAUNCHER_COMPONENT_NAME);
        return enabledSetting != PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
    }
}