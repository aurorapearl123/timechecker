package com.example.ian.timechecker;

import android.Manifest;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import static android.Manifest.permission.SEND_SMS;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {

    private static final ComponentName LAUNCHER_COMPONENT_NAME = new ComponentName(
            "com.example.ian.timechecker", "com.example.ian.timechecker.MainActivity");

    private static final int REQUEST_SMS = 0;

    Button button_login ;
    EditText textUsername, textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        button_login = (Button) this.findViewById(R.id.id_button_login);
        textUsername = (EditText) this.findViewById(R.id.id_username);
        textPassword = (EditText) this.findViewById(R.id.id_password);

        button_login.setOnClickListener(this);



    }


    public void prepareSms()
    {
        String phone = "09335600472";

        Log.wtf("HOMEPAGE", "THIS HOMEPAGE");
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
            sendMySMS(phone);
        }
    }

    public void sendMySMS(String phone_number) {
        //Check if the phoneNumber is empty
        if (phone_number.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please Enter a Valid Phone Number", Toast.LENGTH_SHORT).show();
        } else {

                SmsManager sms = SmsManager.getDefault();
                String message = "Hello There rosales";
                List<String> messages = sms.divideMessage(message);
                for (String msg : messages) {

                    PendingIntent sentIntent = PendingIntent.getBroadcast(this, 0, new Intent("SMS_SENT"), 0);
                    PendingIntent deliveredIntent = PendingIntent.getBroadcast(this, 0, new Intent("SMS_DELIVERED"), 0);
                    sms.sendTextMessage(phone_number, null, msg, sentIntent, deliveredIntent);

                }
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new android.support.v7.app.AlertDialog.Builder(HomePageActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    public void testMethod(String message)
    {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

            Log.wtf("START-THIS", "on start this");
            //...
            //SmsReceiver smsReceiver = new SmsReceiver(this); //passing context
            //LocalBroadcastManager.getInstance(this).registerReceiver(smsReceiver,null);
            //...

       // TimeChangedReceiver timeChangedReceiver = new TimeChangedReceiver(this);
        //LocalBroadcastManager.getInstance(this).registerReceiver(timeChangedReceiver, null);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_button_login:
                String username = textUsername.getText().toString();
                String password = textPassword.getText().toString();

                if(username.equals("superadmin") && password.equals("superadmin123")) {
                    startActivity(new Intent(this, DashboardActivity.class));
                    textUsername.setText("");
                    textPassword.setText("");
                }
                else {
                    Toast.makeText(this, "User not found.", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
