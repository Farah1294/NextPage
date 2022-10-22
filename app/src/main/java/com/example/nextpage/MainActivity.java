package com.example.nextpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
     Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSend = findViewById(R.id.btnSend);

        }

    public void PageSend(View view) {
        Intent intent = new Intent(MainActivity.this,SendSms.class);
        startActivity(intent);
    }

    public void PageReceive(View view) {
        Intent intent = new Intent(MainActivity.this,ReceiveSms.class);
        startActivity(intent);
    }
}
