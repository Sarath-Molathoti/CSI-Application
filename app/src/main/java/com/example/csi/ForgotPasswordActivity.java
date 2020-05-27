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

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText fRollNo;
    private EditText fNewPassword;
    private EditText fConPassword;
    private Button fChangeBtn;
    private ProgressDialog mRegProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        fRollNo = (EditText) findViewById(R.id.pforgot_roll_no);
        fNewPassword = (EditText) findViewById(R.id.pforgot_password);
        fConPassword = (EditText) findViewById(R.id.pforgot_password_con);
        fChangeBtn = (Button) findViewById(R.id.change_btn);
        mRegProgress = new ProgressDialog(this);

        fChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRegProgress.setTitle("Updating Password...");
                mRegProgress.setMessage("Please wait a second");
                mRegProgress.setCanceledOnTouchOutside(false);
                mRegProgress.show();
                final String UserEnteredRollNo = fRollNo.getText().toString();
                final String UserEnteredPassword = fNewPassword.getText().toString();
                final String UserEnteredConPassword = fConPassword.getText().toString();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                Query checkUser = reference.orderByChild("roll_no").equalTo(UserEnteredRollNo);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            if(UserEnteredPassword.equals(UserEnteredConPassword)){
                                mRegProgress.dismiss();
                                DatabaseReference reference9 = FirebaseDatabase.getInstance().getReference("Users");
                                String parentNameFromDb = dataSnapshot.child(UserEnteredRollNo).child("parent_name").getValue(String.class);
                                String studentNameFromDb = dataSnapshot.child(UserEnteredRollNo).child("student_name").getValue(String.class);
                                String emailFromDb = dataSnapshot.child(UserEnteredRollNo).child("email").getValue(String.class);
                                String faEmailFromDb = dataSnapshot.child(UserEnteredRollNo).child("fa_email").getValue(String.class);
                                String wardenEmailFromDb = dataSnapshot.child(UserEnteredRollNo).child("warden_email").getValue(String.class);
                                String mobileFromDb = dataSnapshot.child(UserEnteredRollNo).child("mobile").getValue(String.class);
                                String addressFromDb = dataSnapshot.child(UserEnteredRollNo).child("address").getValue(String.class);
                                UserHelperClass helperClass = new UserHelperClass(studentNameFromDb,parentNameFromDb,UserEnteredRollNo,mobileFromDb,emailFromDb,addressFromDb,faEmailFromDb,wardenEmailFromDb,UserEnteredPassword);
                                reference9.child(UserEnteredRollNo).setValue(helperClass);
                                Intent new_intent = new Intent(ForgotPasswordActivity.this,ParentLoginActivity.class);
                                startActivity(new_intent);
                                finish();

                            }else{
                                mRegProgress.hide();
                                Toast.makeText(ForgotPasswordActivity.this, "New Password and Confirm Password should be same", Toast.LENGTH_SHORT).show();
                            }
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
