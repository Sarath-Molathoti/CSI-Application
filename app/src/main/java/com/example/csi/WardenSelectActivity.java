package com.example.csi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WardenSelectActivity extends AppCompatActivity {

    private Button mWardenSendMsg;
    private Button mWardenViewMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warden_select);
        Toolbar toolbar = findViewById(R.id.myToolbar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("CSI");

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.logout){
            Intent intent1 = new Intent(WardenSelectActivity.this,WardenLoginActivity.class);
            startActivity(intent1);
            finish();
            Toast.makeText(this, "Logged out Successfully", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}
