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

public class WrSendMsgActivity extends AppCompatActivity {

    private EditText wStudentRollNo;
    private EditText wWardenMessage;
    private Button wWardenMsgBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    private ProgressDialog mRegProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wr_send_msg);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        wStudentRollNo = (EditText) findViewById(R.id.w_stu_roll_no);
        wWardenMessage = (EditText) findViewById(R.id.w_msg);
        wWardenMsgBtn = (Button) findViewById(R.id.w_msg_btn);
        mRegProgress = new ProgressDialog(this);


        wWardenMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRegProgress.setTitle("Sending Message");
                mRegProgress.setMessage("Please wait a second");
                mRegProgress.setCanceledOnTouchOutside(false);
                mRegProgress.show();
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Messages");

                Intent intent = getIntent();
                final String wardenName = intent.getStringExtra("w_name");
                final String wardenEmail = intent.getStringExtra("w_email");
                final String wardenUserId = intent.getStringExtra("w_user_id");


                final String WardenStudentRollNo = wStudentRollNo.getText().toString();
                final String wardenMessage = wWardenMessage.getText().toString();

                DatabaseReference reference6 = FirebaseDatabase.getInstance().getReference("Users");

                Query checkUser = reference6.orderByChild("roll_no").equalTo(WardenStudentRollNo);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            mRegProgress.dismiss();
                            String parentEmailFromDb = dataSnapshot.child(WardenStudentRollNo).child("email").getValue(String.class);
                            String wardenEmailFromDb = dataSnapshot.child(WardenStudentRollNo).child("warden_email").getValue(String.class);

                            if(wardenEmail.equals(wardenEmailFromDb)){
                                String id = reference.push().getKey();
                                UserHelperClass2 helperClass6 = new UserHelperClass2(id,wardenEmail,parentEmailFromDb,wardenMessage);
                                reference.child(id).setValue(helperClass6);

                                Toast.makeText(WrSendMsgActivity.this, "Message Sent Successfully", Toast.LENGTH_SHORT).show();

                                Intent wViewMsgIntent = new Intent(WrSendMsgActivity.this,WardenViewMsgActivity.class);
                                wViewMsgIntent.putExtra("parent_email",parentEmailFromDb);
                                wViewMsgIntent.putExtra("w_email",wardenEmail);
                                wViewMsgIntent.putExtra("w_name",wardenName);
                                wViewMsgIntent.putExtra("w_user_id",wardenUserId);
                                wViewMsgIntent.putExtra("roll_no",WardenStudentRollNo);
                                startActivity(wViewMsgIntent);

                            }else{
                                mRegProgress.hide();
                                Toast.makeText(WrSendMsgActivity.this, "You are not his/her Warden. You can't message him/her", Toast.LENGTH_SHORT).show();
                            }


                        }else{
                            mRegProgress.hide();
                            Toast.makeText(WrSendMsgActivity.this, "Invalid Student Roll No", Toast.LENGTH_SHORT).show();
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
