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

public class FaLoginActivity extends AppCompatActivity {

    private EditText fEnteredUserId;
    private EditText fEnteredPassword;
    private Button fFaLoginBtn;

    private ProgressDialog mRegProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_login);

        fEnteredUserId = (EditText) findViewById(R.id.fa_user_id);
        fEnteredPassword = (EditText) findViewById(R.id.fa_password);
        fFaLoginBtn = (Button) findViewById(R.id.fa_login_btn);

        mRegProgress = new ProgressDialog(this);


        fFaLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRegProgress.setTitle("Checking for User");
                mRegProgress.setMessage("Please wait a second");
                mRegProgress.setCanceledOnTouchOutside(false);
                mRegProgress.show();
                isFa();
            }
        });
    }

    public void isFa(){
        final String FaEnteredUserId = fEnteredUserId.getText().toString();
        final String FaEnteredPassword = fEnteredPassword.getText().toString();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Fa");

        Query checkUser = reference.orderByChild("fa_user_id").equalTo(FaEnteredUserId);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    //String FaDbPassword = dataSnapshot.child(FaEnteredUserId).child("f_name").getValue(String.class);
                    String FaPasswordFromDb = dataSnapshot.child(FaEnteredUserId).child("fa_password").getValue(String.class);

                   if(FaPasswordFromDb.equals(FaEnteredPassword)){
                       mRegProgress.dismiss();


                       String faNameFromDb = dataSnapshot.child(FaEnteredUserId).child("fa_name").getValue(String.class);
                       String faEmailFromDb = dataSnapshot.child(FaEnteredUserId).child("fa_email").getValue(String.class);
                       //String emailFromDb = dataSnapshot.child(UserEnteredRollNo).child("email").getValue(String.class);

                        Toast.makeText(FaLoginActivity.this, "Login SuccessFull", Toast.LENGTH_SHORT).show();
                        Intent faSendIntent = new Intent(FaLoginActivity.this,FaSelectActivity.class);
                        faSendIntent.putExtra("f_name",faNameFromDb);
                        faSendIntent.putExtra("f_email",faEmailFromDb);
                        faSendIntent.putExtra("f_user_id",FaEnteredUserId);
                        startActivity(faSendIntent);
                        finish();
                   }
                    else{
                       mRegProgress.hide();

                       Toast.makeText(FaLoginActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    mRegProgress.hide();

                    Toast.makeText(FaLoginActivity.this, "No Such User Exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
