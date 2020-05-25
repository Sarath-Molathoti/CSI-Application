package com.example.csi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MessageToWardenActivity extends AppCompatActivity {

    private EditText mMessageToWarden;
    private Button mMessageToWardenBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference3;

    private ProgressDialog mRegProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_to_warden);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        mMessageToWarden = (EditText) findViewById(R.id.p_to_w_msg);
        mMessageToWardenBtn = (Button) findViewById(R.id.p_to_w_msg_btn);

        mRegProgress = new ProgressDialog(this);


        mMessageToWardenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRegProgress.setTitle("Sending Message");
                mRegProgress.setMessage("Please wait a second");
                mRegProgress.setCanceledOnTouchOutside(false);
                mRegProgress.show();
                rootNode = FirebaseDatabase.getInstance();
                reference3 = rootNode.getReference("Messages");

                Intent intent = getIntent();
                String aParentName = intent.getStringExtra("parent_name");
                String aStudentName = intent.getStringExtra("student_name");
                String aFrom = intent.getStringExtra("email");
                String aFaEmail = intent.getStringExtra("fa_email");
                String aTo = intent.getStringExtra("warden_email");
                String aRollNo = intent.getStringExtra("roll_no");

                String message = mMessageToWarden.getText().toString();
                String id = reference3.push().getKey();

                UserHelperClass3 helperClass3 = new UserHelperClass3(aFrom,aTo,message,id);
                reference3.child(id).setValue(helperClass3);
                mRegProgress.dismiss();

                Toast.makeText(MessageToWardenActivity.this, "Message Sent Successfully", Toast.LENGTH_SHORT).show();

                Intent showFromW_intent = new Intent(MessageToWardenActivity.this,MessageFromWardenActivity.class);
                showFromW_intent.putExtra("parent_name",aParentName);
                showFromW_intent.putExtra("student_name",aStudentName);
                showFromW_intent.putExtra("email",aFrom);
                showFromW_intent.putExtra("fa_email",aFaEmail);
                showFromW_intent.putExtra("warden_email",aTo);
                showFromW_intent.putExtra("roll_no",aRollNo);
                startActivity(showFromW_intent);



            }
        });


    }
}
