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
                String pParentName = intent.getStringExtra("parent_name");
                String pStudentName = intent.getStringExtra("student_name");
                String pFrom = intent.getStringExtra("email");
                String pTo = intent.getStringExtra("fa_email");
                String pWardenEmail = intent.getStringExtra("warden_email");
                String pRollNo = intent.getStringExtra("roll_no");

                String message = mMessageToFa.getText().toString();
                String id = reference.push().getKey();

                UserHelperClass2 helperClass2 = new UserHelperClass2(id,pFrom,pTo,message);
                reference.child(id).setValue(helperClass2);

                Intent showFromFa_intent = new Intent(MessageToFaActivity.this,ShowMsgsFromFaActivity.class);
                showFromFa_intent.putExtra("parent_name",pParentName);
                showFromFa_intent.putExtra("student_name",pStudentName);
                showFromFa_intent.putExtra("email",pFrom);
                showFromFa_intent.putExtra("fa_email",pTo);
                showFromFa_intent.putExtra("Warden_email",pWardenEmail);
                showFromFa_intent.putExtra("roll_no",pRollNo);
                startActivity(showFromFa_intent);



            }
        });

    }
}
