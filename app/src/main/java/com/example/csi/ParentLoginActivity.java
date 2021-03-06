package com.example.csi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ParentLoginActivity extends AppCompatActivity {
    private EditText mEnteredRollNo;
    private EditText mEnteredPassword;
    private Button mLoginBtn;
    private Button mCreateAccount;
    private Button mForgotPassword;

    private ProgressDialog mRegProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        mEnteredRollNo = (EditText) findViewById(R.id.plogin_roll_no);
        mEnteredPassword = (EditText) findViewById(R.id.plogin_password);
        mLoginBtn = (Button) findViewById(R.id.login_btn);
        mCreateAccount = (Button) findViewById(R.id.create_acc);
        mForgotPassword = (Button) findViewById(R.id.forgot);

        mForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgot_intent = new Intent(ParentLoginActivity.this,ForgotPasswordActivity.class);
                startActivity(forgot_intent);
            }
        });

        mCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent create_intent = new Intent(ParentLoginActivity.this,ParentRegistrationActivity.class);
                startActivity(create_intent);
            }
        });

        mRegProgress = new ProgressDialog(this);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRegProgress.setTitle("Checking for User");
                mRegProgress.setMessage("Please wait a second");
                mRegProgress.setCanceledOnTouchOutside(false);
                mRegProgress.show();
                isUser();
            }
        });

    }

    public void isUser() {
        final String UserEnteredRollNo = mEnteredRollNo.getText().toString();
        final String UserEnteredPassword = mEnteredPassword.getText().toString();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        Query checkUser = reference.orderByChild("roll_no").equalTo(UserEnteredRollNo);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String passwordFromDb = dataSnapshot.child(UserEnteredRollNo).child("password").getValue(String.class);

                    if(passwordFromDb.equals(UserEnteredPassword)){
                        mRegProgress.dismiss();
                        String parentNameFromDb = dataSnapshot.child(UserEnteredRollNo).child("parent_name").getValue(String.class);
                        String studentNameFromDb = dataSnapshot.child(UserEnteredRollNo).child("student_name").getValue(String.class);
                        String emailFromDb = dataSnapshot.child(UserEnteredRollNo).child("email").getValue(String.class);
                        String faEmailFromDb = dataSnapshot.child(UserEnteredRollNo).child("fa_email").getValue(String.class);
                        String wardenEmailFromDb = dataSnapshot.child(UserEnteredRollNo).child("warden_email").getValue(String.class);

                        Toast.makeText(ParentLoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                        Intent selectIntent = new Intent(ParentLoginActivity.this,ParentSelectActivity.class);
                        selectIntent.putExtra("parent_name",parentNameFromDb);
                        selectIntent.putExtra("student_name",studentNameFromDb);
                        selectIntent.putExtra("email",emailFromDb);
                        selectIntent.putExtra("fa_email",faEmailFromDb);
                        selectIntent.putExtra("warden_email",wardenEmailFromDb);
                        selectIntent.putExtra("roll_no",UserEnteredRollNo);
                        startActivity(selectIntent);
                        finish();
                   }
                    else{
                        mRegProgress.hide();
                        Toast.makeText(ParentLoginActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    mRegProgress.hide();
                    Toast.makeText(ParentLoginActivity.this, "No Such User Exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
