package com.example.csi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FaSendMessageActivity extends AppCompatActivity {

    private EditText mStudentRollNo;
    private EditText mFaMessage;
    private Button mFaMsgBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    private ProgressDialog mRegProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_send_message);

        mStudentRollNo = (EditText) findViewById(R.id.stu_roll_no);
        mFaMessage = (EditText) findViewById(R.id.fa_msg);
        mFaMsgBtn = (Button) findViewById(R.id.fa_msg_btn);
        mRegProgress = new ProgressDialog(this);


        mFaMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRegProgress.setTitle("Sending Message");
                mRegProgress.setMessage("Please wait a second");
                mRegProgress.setCanceledOnTouchOutside(false);
                mRegProgress.show();
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Messages");

                Intent intent = getIntent();
                final String faName = intent.getStringExtra("fac_name");
                final String faEmail = intent.getStringExtra("fac_email");
                final String faUserId = intent.getStringExtra("fac_user_id");


                final String StudentRollNo = mStudentRollNo.getText().toString();
                final String FaMessage = mFaMessage.getText().toString();

                DatabaseReference reference6 = FirebaseDatabase.getInstance().getReference("Users");

                Query checkUser = reference6.orderByChild("roll_no").equalTo(StudentRollNo);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            mRegProgress.dismiss();
                            String parentEmailFromDb = dataSnapshot.child(StudentRollNo).child("email").getValue(String.class);
                            String faEmailFromDb = dataSnapshot.child(StudentRollNo).child("fa_email").getValue(String.class);

                            if(faEmail.equals(faEmailFromDb)){
                                String id = reference.push().getKey();
                                UserHelperClass2 helperClass5 = new UserHelperClass2(id,faEmail,parentEmailFromDb,FaMessage);
                                reference.child(id).setValue(helperClass5);

                                Toast.makeText(FaSendMessageActivity.this, "Message Sent Successfully", Toast.LENGTH_SHORT).show();

                                Intent faViewMsgIntent = new Intent(FaSendMessageActivity.this,FaViewMsgActivity.class);
                                faViewMsgIntent.putExtra("parent_email",parentEmailFromDb);
                                faViewMsgIntent.putExtra("fac_email",faEmailFromDb);
                                faViewMsgIntent.putExtra("fac_name",faName);
                                faViewMsgIntent.putExtra("fac_user_id",faUserId);
                                faViewMsgIntent.putExtra("roll_no",StudentRollNo);
                                startActivity(faViewMsgIntent);

                            }else{
                                mRegProgress.hide();
                                Toast.makeText(FaSendMessageActivity.this, "You are not his/her Fa. You can't message him/her", Toast.LENGTH_SHORT).show();
                            }


                        }else{
                            mRegProgress.hide();
                            Toast.makeText(FaSendMessageActivity.this, "Invalid Student Roll No", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
