package com.example.ian.timechecker;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    public static final String LOG = "CALLTEST";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //String package_name = getApplicationContext().getPackageName();
        //Log.wtf(LOG, "testing me."+package_name+"");

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)  !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_PHONE_STATE}, 1);
        }

//        try{
//            PackageManager p = getPackageManager();
//            p.setComponentEnabledSetting(getComponentName(), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
