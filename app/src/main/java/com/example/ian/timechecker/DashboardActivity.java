package com.example.ian.timechecker;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DashboardActivity extends AppCompatActivity {

    private static final ComponentName LAUNCHER_COMPONENT_NAME = new ComponentName(
            "com.example.ian.timechecker", "com.example.ian.timechecker.MainActivity");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Logout "+isLauncherIconVisible(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //finish();
                //System.exit(0);

                if(isLauncherIconVisible()) {
                    //hide
                    getPackageManager().setComponentEnabledSetting(LAUNCHER_COMPONENT_NAME,
                            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                            PackageManager.DONT_KILL_APP);
                    finish();
                    System.exit(0);
                }
                else {
//                    getPackageManager().setComponentEnabledSetting(LAUNCHER_COMPONENT_NAME,
//                            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
//                            PackageManager.DONT_KILL_APP);
                    finish();
                    System.exit(0);
                }

            }
        });
    }

    private boolean isLauncherIconVisible() {
        int enabledSetting = getPackageManager().getComponentEnabledSetting(LAUNCHER_COMPONENT_NAME);
        return enabledSetting != PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
    }
}
