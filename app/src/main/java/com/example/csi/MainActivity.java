package com.example.csi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mParentBtn;
    private Button mFaBtn;
    private Button mWardenBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mParentBtn = (Button) findViewById(R.id.parent_btn);
        mFaBtn = (Button) findViewById(R.id.fa_btn);
        mWardenBtn = (Button) findViewById(R.id.warden_btn);

        mParentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start_intent = new Intent(MainActivity.this,StartActivity.class);
                startActivity(start_intent);
            }
        });
        mFaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent falogin_intent = new Intent(MainActivity.this,FaLoginActivity.class);
                startActivity(falogin_intent);

            }
        });
        mWardenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wardenlogin_intent = new Intent(MainActivity.this,WardenLoginActivity.class);
                startActivity(wardenlogin_intent);
            }
        });

    }
}
