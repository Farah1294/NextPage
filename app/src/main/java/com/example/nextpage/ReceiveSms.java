package com.example.nextpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ReceiveSms extends AppCompatActivity {

    //Here in MainActivity asking permission
    private static final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 0;
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    TextView tvPhoneNum, tvMsg;

    public  static String PHONENUM ="com.exampe.nextpage.PHONENUM";
    public  static String MSG  ="com.example.nextpage.MSG";

    //Using by sms delete public static string tu and MSG change to msg and PHONENUM change it to phoneno

    MyReceiver receiver = new MyReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
            tvMsg.setText(MSG);
            tvPhoneNum.setText(PHONENUM);
//            tvMsg.setText(msg);
//            tvPhoneNum.setText(phoneNo);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(SMS_RECEIVED));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_sms);

        tvPhoneNum = findViewById(R.id.tvPhoneNum);
        tvMsg= findViewById(R.id.tvMsg);


        Intent intent = getIntent();

        String strPhoneNum = intent.getStringExtra(PHONENUM);
//        String strPhoneNum2 = intent.getStringExtra(phoneNo);
        tvPhoneNum = findViewById(R.id.tvPhoneNum);
        tvPhoneNum.setText(strPhoneNum);
//        tvPhoneNum.setText(strPhoneNum2);

        String strMsg = intent.getStringExtra(MSG);
//        String strMsg2 = intent.getStringExtra(msg);
        tvMsg = findViewById(R.id.tvMsg);
        tvMsg.setText(strMsg);
//        tvMsg.setText(strMsg2);


        //check if permission is not granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            //if the permission is not been granted then check if the user has denied the permission
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {
                //Do nothing as user has denied
            } else {
                //a pop up will appear asking for required permission i.e Allow or Deniy
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, MY_PERMISSIONS_REQUEST_RECEIVE_SMS);
            }
        }
    }

    //onCreate
    //After getting the result of permission request the result will be passed through this method

    public void onRequestPermissionResult(int requestCode, String permissions[], int[] grantResults) {
        //will check the request
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_RECEIVE_SMS: {
                //check whether the length of grantResults is greater than 0 & = to PERMISSION_GRANTED
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Now broadcastReceiver will work in background
                    Toast.makeText(this, "Thank you for permitting", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this, "Permission is denied!", Toast.LENGTH_LONG).show();
                }
            }
        }
        };
    }




