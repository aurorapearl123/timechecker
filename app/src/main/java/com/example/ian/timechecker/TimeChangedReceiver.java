package com.example.ian.timechecker;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.SEND_SMS;

public class TimeChangedReceiver extends BroadcastReceiver {

    HomePageActivity homePageActivity;



    @Override
    public void onReceive(Context context, Intent intent) {
        //String number = "09335600472";
        String number = "09268818030";
        Log.wtf("CHANGE-TIME", "YOU CHANGE THE TIME");

//        SmsManager sms = SmsManager.getDefault();
//
//        String message = "Testing this";
//        List<String> messages = sms.divideMessage(message);
//        for (String msg : messages) {
//
//            PendingIntent sentIntent = PendingIntent.getBroadcast(context, 0, new Intent("SMS_SENT"), 0);
//            PendingIntent deliveredIntent = PendingIntent.getBroadcast(context, 0, new Intent("SMS_DELIVERED"), 0);
//            sms.sendTextMessage(number, null, msg, sentIntent, deliveredIntent);
//
//        }

        //homePageActivity.testMethod("Hello Method");

        sendSms(number ,"Time was Changed.", context);
    }

    public void sendSms(String phonenumber,String message, Context context)
    {
        SmsManager manager = SmsManager.getDefault();

        PendingIntent piSend = PendingIntent.getBroadcast(context, 0, new Intent("SMS_SENT"), 0);
        PendingIntent piDelivered = PendingIntent.getBroadcast(context, 0, new Intent("SMS_DELIVERED"), 0);

        int length = message.length();

        if(length > 50)
        {
            ArrayList<String> messagelist = manager.divideMessage(message);

            manager.sendMultipartTextMessage(phonenumber, null, messagelist, null, null);
        }
        else
        {
            manager.sendTextMessage(phonenumber, null, message, piSend, piDelivered);
        }
    }

}
