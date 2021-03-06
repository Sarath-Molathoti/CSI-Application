package com.example.csi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ParentSelectActivity extends AppCompatActivity {

    private Button mMessagesFromWardenBtn;
    private Button mMessagesFromFaBtn;
    private Button mMessageToWardenBtn;
    private Button mMessageToFaBtn;
   // private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_select);
        Toolbar toolbar = findViewById(R.id.myToolbar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("CSI");

        mMessagesFromFaBtn = (Button) findViewById(R.id.messages_from_fa_btn);
        mMessagesFromWardenBtn = (Button) findViewById(R.id.messages_from_warden_btn);
        mMessageToFaBtn = (Button) findViewById(R.id.message_to_fa_btn);
        mMessageToWardenBtn= (Button) findViewById(R.id.message_to_warden_btn);

        Intent intent = getIntent();
        final String sParentName = intent.getStringExtra("parent_name");
        final String sStudentName = intent.getStringExtra("student_name");
        final String sEmail = intent.getStringExtra("email");
        final String sFaEmail = intent.getStringExtra("fa_email");
        final String sWardenEmail = intent.getStringExtra("warden_email");
        final String sRollNo = intent.getStringExtra("roll_no");



        mMessagesFromFaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent messageFromFa_intent = new Intent(ParentSelectActivity.this,ShowMsgsFromFaActivity.class);
                messageFromFa_intent.putExtra("parent_name",sParentName);
                messageFromFa_intent.putExtra("student_name",sStudentName);
                messageFromFa_intent.putExtra("email",sEmail);
                messageFromFa_intent.putExtra("fa_email",sFaEmail);
                messageFromFa_intent.putExtra("warden_email",sWardenEmail);
                messageFromFa_intent.putExtra("roll_no",sRollNo);
                startActivity(messageFromFa_intent);
            }
        });
        mMessagesFromWardenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent messageFromWarden_intent = new Intent(ParentSelectActivity.this,MessageFromWardenActivity.class);
                messageFromWarden_intent.putExtra("parent_name",sParentName);
                messageFromWarden_intent.putExtra("student_name",sStudentName);
                messageFromWarden_intent.putExtra("email",sEmail);
                messageFromWarden_intent.putExtra("fa_email",sFaEmail);
                messageFromWarden_intent.putExtra("warden_email",sWardenEmail);
                messageFromWarden_intent.putExtra("roll_no",sRollNo);
                startActivity(messageFromWarden_intent);
            }
        });
        mMessageToFaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent messageToFa_intent = new Intent(ParentSelectActivity.this,MessageToFaActivity.class);
                messageToFa_intent.putExtra("parent_name",sParentName);
                messageToFa_intent.putExtra("student_name",sStudentName);
                messageToFa_intent.putExtra("email",sEmail);
                messageToFa_intent.putExtra("fa_email",sFaEmail);
                messageToFa_intent.putExtra("warden_email",sWardenEmail);
                messageToFa_intent.putExtra("roll_no",sRollNo);
                startActivity(messageToFa_intent);
            }
        });
        mMessageToWardenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent messageToWarden_intent = new Intent(ParentSelectActivity.this,MessageToWardenActivity.class);
                messageToWarden_intent.putExtra("parent_name",sParentName);
                messageToWarden_intent.putExtra("student_name",sStudentName);
                messageToWarden_intent.putExtra("email",sEmail);
                messageToWarden_intent.putExtra("fa_email",sFaEmail);
                messageToWarden_intent.putExtra("warden_email",sWardenEmail);
                messageToWarden_intent.putExtra("roll_no",sRollNo);
                startActivity(messageToWarden_intent);
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
            Intent intent1 = new Intent(ParentSelectActivity.this,ParentLoginActivity.class);
            startActivity(intent1);
            finish();
            Toast.makeText(this, "Logged out Successfully", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}
