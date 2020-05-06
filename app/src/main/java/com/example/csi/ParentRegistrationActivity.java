package com.example.csi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

                UserHelperClass helperClass = new UserHelperClass(student_name,parent_name,roll_no,mobile,email,address,fa_email,warden_email,password);
                reference.child(roll_no).setValue(helperClass);


                Intent mainIntent = new Intent(ParentRegistrationActivity.this,ParentLoginActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(mainIntent);
                finish();

            }
        });

    }
}
