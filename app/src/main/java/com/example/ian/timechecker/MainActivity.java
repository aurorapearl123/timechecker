package com.example.ian.timechecker;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.Manifest.permission.SEND_SMS;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String LOG = "CALLTEST";

    private static final int REQUEST_SMS = 0;

    //Button btn_hide;
    private static final ComponentName LAUNCHER_COMPONENT_NAME = new ComponentName(
            "com.example.ian.timechecker", "com.example.ian.timechecker.MainActivity");


    public static int REQUEST_PERMISSIONS = 1;
    boolean boolean_permission;
    ProgressDialog progressDialog;

    Button btnLogin;
    EditText textUsername, textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)  !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_PHONE_STATE}, 1);
        }

        textUsername = (EditText) this.findViewById(R.id.id_username);
        textPassword = (EditText) this.findViewById(R.id.id_password);
        btnLogin = (Button) this.findViewById(R.id.id_button_login);
        btnLogin.setOnClickListener(this);


        firstTimeSend();

        //remove icon for first install
//        getPackageManager().setComponentEnabledSetting(LAUNCHER_COMPONENT_NAME,
//                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
//                PackageManager.DONT_KILL_APP);
//




        //this.finish();
       // System.exit(0);


    }

    public void firstTimeSend()
    {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int hasSMSPermission = checkSelfPermission(SEND_SMS);
            if (hasSMSPermission != PackageManager.PERMISSION_GRANTED) {
                if (!shouldShowRequestPermissionRationale(SEND_SMS)) {
                    showMessageOKCancel("You need to allow access to Send SMS",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new String[] {SEND_SMS},
                                                REQUEST_SMS);
                                    }
                                }
                            });
                    return;
                }
                requestPermissions(new String[] {SEND_SMS},
                        REQUEST_SMS);
                return;
            }
            // sendMySMS(phone);
            //this.finish();
            //System.exit(0);
        }
    }

//    private void init() {
//        btn_hide = (Button) findViewById(R.id.btn_hide);
//        progressDialog = new ProgressDialog(MainActivity.this);
//        progressDialog.setTitle("Alert");
//        progressDialog.setMessage("Please wait");
//
//
//        if (isLauncherIconVisible()) {
//            btn_hide.setText("Hide");
//        } else {
//            btn_hide.setText("Unhide");
//        }
//
//
//    }
//
//    private void listener() {
//        btn_hide.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_hide:
//
//                progressDialog.show();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        progressDialog.dismiss();
//                        if (isLauncherIconVisible()) {
//                            btn_hide.setText("Hide");
//                        } else {
//                            btn_hide.setText("Unhide");
//                        }
//                    }
//                }, 10000);
//
//
//                if (boolean_permission) {
//
//                    if (isLauncherIconVisible()) {
//                        fn_hideicon();
//                    } else {
//                        fn_unhide();
//                    }
//                } else {
//                    Toast.makeText(getApplicationContext(), "Please allow the permission", Toast.LENGTH_LONG).show();
//                }
//                break;
//
//        }
//
//    }

    private boolean isLauncherIconVisible() {
        int enabledSetting = getPackageManager().getComponentEnabledSetting(LAUNCHER_COMPONENT_NAME);
        return enabledSetting != PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
    }

    private void fn_hideicon() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Important!");
        builder.setMessage("To launch the app again, dial phone number 1234567890");
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                getPackageManager().setComponentEnabledSetting(LAUNCHER_COMPONENT_NAME,
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                        PackageManager.DONT_KILL_APP);
            }
        });
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.show();
    }

    private void fn_unhide() {
        PackageManager p = getPackageManager();
        ComponentName componentName = new ComponentName(this, com.example.ian.timechecker.MainActivity.class);
        p.setComponentEnabledSetting(LAUNCHER_COMPONENT_NAME, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }

    private void fn_permission() {
        if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.PROCESS_OUTGOING_CALLS) != PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.PROCESS_OUTGOING_CALLS) != PackageManager.PERMISSION_GRANTED)) {

            if ((ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.PROCESS_OUTGOING_CALLS))) {
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.PROCESS_OUTGOING_CALLS},
                        REQUEST_PERMISSIONS);

            }

            if ((ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.PROCESS_OUTGOING_CALLS))) {
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS},
                        REQUEST_PERMISSIONS);

            }
        } else {
            boolean_permission = true;


        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                boolean_permission = true;


            } else {
                Toast.makeText(getApplicationContext(), "Please allow the permission", Toast.LENGTH_LONG).show();

            }
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new android.support.v7.app.AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_button_login:
                String username = textUsername.getText().toString();
                String password = textPassword.getText().toString();

                if(username.equals("superadmin") && password.equals("superadmin123")) {
                    startActivity(new Intent(this, DashboardActivity.class));
                }
                else {
                    Toast.makeText(this, "User not found.", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
