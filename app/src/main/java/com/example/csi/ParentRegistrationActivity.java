package com.example.csi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class ParentRegistrationActivity extends AppCompatActivity {

    EditText mStudentName;
    EditText mParentName;
    EditText mEmail;
    EditText mMobile;
    EditText mAddress;
    EditText mPassword;
    EditText mFaEmail;
    EditText mWardenEmail;
    EditText mRollNo;
    Button mRegister;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_registration);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        mStudentName = (EditText) findViewById(R.id.student_name);
        mParentName = (EditText) findViewById(R.id.parent_name);
        mEmail = (EditText) findViewById(R.id.parent_email);
        mMobile = (EditText) findViewById(R.id.mobile);
        mAddress = (EditText) findViewById(R.id.address);
        mPassword = (EditText) findViewById(R.id.password);
        mFaEmail = (EditText) findViewById(R.id.fa_email);
        mWardenEmail = (EditText) findViewById(R.id.warden_email);
        mRollNo = (EditText) findViewById(R.id.roll_no);
        mRegister = (Button) findViewById(R.id.register_btn);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");

                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                String student_name = mStudentName.getText().toString();
                String parent_name = mParentName.getText().toString();
                String mobile = mMobile.getText().toString();
                String address = mAddress.getText().toString();
                String roll_no = mRollNo.getText().toString();
                String fa_email = mFaEmail.getText().toString();
                String warden_email = mWardenEmail.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String rollNoPattern = "[BM][0-9][0-9][0-9][0-9][0-9][0-9][A-Z][A-Z]$";


                if(email.isEmpty() || password.isEmpty() || student_name.isEmpty() || parent_name.isEmpty() || mobile.isEmpty() || address.isEmpty() || roll_no.isEmpty() || fa_email.isEmpty() || warden_email.isEmpty()){
                    Toast.makeText(ParentRegistrationActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }else if(mobile.length() != 10){
                    Toast.makeText(ParentRegistrationActivity.this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
                }else if(password.length() < 6){
                    Toast.makeText(ParentRegistrationActivity.this, "Password is too short", Toast.LENGTH_SHORT).show();
                }else if(!email.matches(emailPattern)){
                    Toast.makeText(ParentRegistrationActivity.this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
                }else if(!roll_no.matches(rollNoPattern)){
                    Toast.makeText(ParentRegistrationActivity.this, "Please Enter a valid NITC Roll No in CAPITAL LETTERS", Toast.LENGTH_SHORT).show();
                }
                else {

                    DatabaseReference reference6 = FirebaseDatabase.getInstance().getReference("Users");

                    final Query checkUser = reference6.orderByChild("roll_no").equalTo(roll_no);
                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                Toast.makeText(ParentRegistrationActivity.this, "User With this Roll No already exists", Toast.LENGTH_SHORT).show();

                            }else{
                                String email = mEmail.getText().toString();
                                String password = mPassword.getText().toString();
                                String student_name = mStudentName.getText().toString();
                                String parent_name = mParentName.getText().toString();
                                String mobile = mMobile.getText().toString();
                                String address = mAddress.getText().toString();
                                String roll_no = mRollNo.getText().toString();
                                String fa_email = mFaEmail.getText().toString();
                                final String warden_email = mWardenEmail.getText().toString();

                                DatabaseReference reference7 = FirebaseDatabase.getInstance().getReference("Fa");

                                Query checkUser2 = reference7.orderByChild("fa_email").equalTo(fa_email);
                                checkUser2.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if(!dataSnapshot.exists()){
                                            Toast.makeText(ParentRegistrationActivity.this, "Please Enter Valid NITC Fa Email/Entered Fa Email does not exist in database ", Toast.LENGTH_SHORT).show();

                                        }else{

                                            DatabaseReference reference8 = FirebaseDatabase.getInstance().getReference("Warden");

                                            final Query checkUser3 = reference8.orderByChild("warden_email").equalTo(warden_email);

                                            checkUser3.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    if(!dataSnapshot.exists()){
                                                        Toast.makeText(ParentRegistrationActivity.this, "Please Enter Valid NITC Warden Email/Entered warden Email does not exist in database ", Toast.LENGTH_SHORT).show();

                                                    }else{
                                                        String email = mEmail.getText().toString();
                                                        String password = mPassword.getText().toString();
                                                        String student_name = mStudentName.getText().toString();
                                                        String parent_name = mParentName.getText().toString();
                                                        String mobile = mMobile.getText().toString();
                                                        String address = mAddress.getText().toString();
                                                        String roll_no = mRollNo.getText().toString();
                                                        String fa_email = mFaEmail.getText().toString();
                                                        String warden_email = mWardenEmail.getText().toString();
                                                        UserHelperClass helperClass = new UserHelperClass(student_name,parent_name,roll_no,mobile,email,address,fa_email,warden_email,password);
                                                        reference.child(roll_no).setValue(helperClass);


                                                        Intent mainIntent = new Intent(ParentRegistrationActivity.this,ParentLoginActivity.class);
                                                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                        startActivity(mainIntent);
                                                        finish();
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });


                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }



            }
        });



    }
}
