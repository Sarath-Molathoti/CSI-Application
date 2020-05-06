package com.example.csi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MessageToFaActivity extends AppCompatActivity {

    private EditText mMessageToFa;
    private Button mMessageToFaBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_to_fa);

        mMessageToFa = (EditText) findViewById(R.id.p_to_fa_msg);
        mMessageToFaBtn = (Button) findViewById(R.id.p_to_fa_msg_btn);


        mMessageToFaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Messages");

                Intent intent = getIntent();
                final String pParentName = intent.getStringExtra("parent_name");
                final String pStudentName = intent.getStringExtra("student_name");
                final String from = intent.getStringExtra("email");
                final String to = intent.getStringExtra("fa_email");
                final String pWardenEmail = intent.getStringExtra("warden_email");
                final String pRollNo = intent.getStringExtra("roll_no");

                String message = mMessageToFa.getText().toString();

                UserHelperClass2 helperClass2 = new UserHelperClass2(from,to,message);
                reference.setValue(helperClass2);



            }
        });

    }
}
