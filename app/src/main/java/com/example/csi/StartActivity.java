package com.example.csi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    private Button mSignUpBtn;
    private Button mSignInBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mSignInBtn = (Button) findViewById(R.id.signin_btn);
        mSignUpBtn = (Button) findViewById(R.id.signup_btn);

        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent parent_registration_intent = new Intent(StartActivity.this,ParentRegistrationActivity.class);
                startActivity(parent_registration_intent);
                finish();
            }
        });
        mSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent parent_login_intent = new Intent(StartActivity.this,ParentLoginActivity.class);
                startActivity(parent_login_intent);
                finish();
            }
        });
    }
}
