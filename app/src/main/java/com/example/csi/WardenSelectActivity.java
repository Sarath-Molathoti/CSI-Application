package com.example.csi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WardenSelectActivity extends AppCompatActivity {

    private Button mWardenSendMsg;
    private Button mWardenViewMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warden_select);

        mWardenSendMsg = (Button) findViewById(R.id.w_send_msg_btn);
        mWardenViewMsg = (Button) findViewById(R.id.w_view_msg_btn);

        Intent intent = getIntent();
        final String vWardenName = intent.getStringExtra("wr_name");
        final String vWardenEmail = intent.getStringExtra("wr_email");
        final String vWardenUserId = intent.getStringExtra("wr_user_id");

        mWardenSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wSendIntent = new Intent(WardenSelectActivity.this,WrSendMsgActivity.class);
                wSendIntent.putExtra("w_name",vWardenName);
                wSendIntent.putExtra("w_email",vWardenEmail);
                wSendIntent.putExtra("w_user_id",vWardenUserId);
                startActivity(wSendIntent);
            }
        });

        mWardenViewMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wSendIntent = new Intent(WardenSelectActivity.this,WardenViewMsgActivity.class);
                wSendIntent.putExtra("w_name",vWardenName);
                wSendIntent.putExtra("w_email",vWardenEmail);
                wSendIntent.putExtra("w_user_id",vWardenUserId);
                startActivity(wSendIntent);
            }
        });
    }
}
