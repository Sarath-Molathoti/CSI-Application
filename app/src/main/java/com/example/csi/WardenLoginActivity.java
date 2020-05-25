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

public class WardenLoginActivity extends AppCompatActivity {

    private EditText wEnteredUserId;
    private EditText wEnteredPassword;
    private Button wWardenLoginBtn;

    private ProgressDialog mRegProgress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warden_login);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        wEnteredUserId = (EditText) findViewById(R.id.w_login_user_id);
        wEnteredPassword = (EditText) findViewById(R.id.w_login_password);
        wWardenLoginBtn = (Button) findViewById(R.id.w_login_btn);

        mRegProgress = new ProgressDialog(this);


        wWardenLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRegProgress.setTitle("Checking for User");
                mRegProgress.setMessage("Please wait a second");
                mRegProgress.setCanceledOnTouchOutside(false);
                mRegProgress.show();
                isWarden();
            }
        });
    }

    public void isWarden(){
        final String WardenEnteredUserId = wEnteredUserId.getText().toString();
        final String WardenEnteredPassword = wEnteredPassword.getText().toString();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Warden");

        Query checkUser = reference.orderByChild("warden_user_id").equalTo(WardenEnteredUserId);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    //String FaDbPassword = dataSnapshot.child(FaEnteredUserId).child("f_name").getValue(String.class);
                    String WardenPasswordFromDb = dataSnapshot.child(WardenEnteredUserId).child("warden_password").getValue(String.class);

                    if(WardenPasswordFromDb.equals(WardenEnteredPassword)){
                        mRegProgress.dismiss();


                        String wardenNameFromDb = dataSnapshot.child(WardenEnteredUserId).child("warden_name").getValue(String.class);
                        String wardenEmailFromDb = dataSnapshot.child(WardenEnteredUserId).child("warden_email").getValue(String.class);
                        //String emailFromDb = dataSnapshot.child(UserEnteredRollNo).child("email").getValue(String.class);

                        Toast.makeText(WardenLoginActivity.this, "Login SuccessFull", Toast.LENGTH_SHORT).show();
                        Intent wSendIntent = new Intent(WardenLoginActivity.this,WardenSelectActivity.class);
                        wSendIntent.putExtra("wr_name",wardenNameFromDb);
                        wSendIntent.putExtra("wr_email",wardenEmailFromDb);
                        wSendIntent.putExtra("wr_user_id",WardenEnteredUserId);
                        startActivity(wSendIntent);
                        finish();
                    }
                    else{
                        mRegProgress.hide();

                        Toast.makeText(WardenLoginActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    mRegProgress.hide();

                    Toast.makeText(WardenLoginActivity.this, "No Such User Exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
