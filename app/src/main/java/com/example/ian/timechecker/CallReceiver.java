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
            "com.example", "com.example.ian.timechecker.MainActivity");

//    private static final ComponentName LAUNCHER_COMPONENT_NAME = new ComponentName(
//            "com.deepshikha.hideappicon", "com.deepshikha.hideappicon.Launcher");

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.wtf("intent-test" , intent.getAction().toString());

//        if(intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
//
//            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
//
//            if(incomingNumber.equals("#7878*")) {
//                showToast(context, "call started..."+incomingNumber);
//                //com.example.ian.timechecker
//                //setResultData(null);
//
//
////                if (isLauncherIconVisible(context)) {
////                    showToast(context, "inside if statement.");
////
////                } else {
//                    Intent i = new Intent();
//                    i.setClassName("com.example", "com.example.ian.timechecker.MainActivity");
//                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(i);
//
////                Intent appIntent = new Intent(context, MainActivity.class);
////                appIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                context.startActivity(appIntent);
//
//               // }
//            }
//            else {
//                showToast(context, "Not equal..."+incomingNumber);
//            }
//
//
//
//
//        }
//        else if(intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_IDLE)){
//            showToast(context, "Call ended..");
//        }

        String phoneNubmer = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        if (LAUNCHER_NUMBER.equals(phoneNubmer)) {
            setResultData(null);

            if (isLauncherIconVisible(context)) {
                //showToast(context, "Inside if..");
//                Intent appIntent = new Intent(context, MainActivity.class);
//                appIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(appIntent);
                //fn_unhide(context);
                Intent appIntent = new Intent(context, HomePageActivity.class);
                appIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(appIntent);
            } else {

            }


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

    private void fn_unhide(Context context) {
        PackageManager p = context.getPackageManager();
        ComponentName componentName = new ComponentName(context, com.example.ian.timechecker.MainActivity.class);
        p.setComponentEnabledSetting(LAUNCHER_COMPONENT_NAME, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }
}