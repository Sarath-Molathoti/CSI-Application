package com.example.csi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FaSelectActivity extends AppCompatActivity {

    private Button mFaSendMsg;
    private Button mFaViewMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_select);

        mFaSendMsg = (Button) findViewById(R.id.fa_send_msg_btn);
        mFaViewMsg = (Button) findViewById(R.id.fa_view_msg_btn);

        Intent intent = getIntent();
        final String vFaName = intent.getStringExtra("f_name");
        final String vFaEmail = intent.getStringExtra("f_email");
        final String vFaUserId = intent.getStringExtra("f_user_id");

        mFaSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent faSendIntent = new Intent(FaSelectActivity.this,FaSendMessageActivity.class);
                faSendIntent.putExtra("fac_name",vFaName);
                faSendIntent.putExtra("fac_email",vFaEmail);
                faSendIntent.putExtra("fac_user_id",vFaUserId);
                startActivity(faSendIntent);
            }
        });

        mFaViewMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent faSendIntent = new Intent(FaSelectActivity.this,FaViewMsgActivity.class);
                faSendIntent.putExtra("fac_name",vFaName);
                faSendIntent.putExtra("fac_email",vFaEmail);
                faSendIntent.putExtra("fac_user_id",vFaUserId);
                startActivity(faSendIntent);
            }
        });
    }
}
