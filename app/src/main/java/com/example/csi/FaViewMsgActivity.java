package com.example.csi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FaViewMsgActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_view_msg);

        mListView = (ListView) findViewById(R.id.listView3);

        Intent intent = getIntent();
       // final String rParentEmail = intent.getStringExtra("parent_email");
        final String rFaEmail = intent.getStringExtra("fac_email");
        final String rFaName = intent.getStringExtra("fac_name");
        final String rFaUserId = intent.getStringExtra("fac_user_id");
       // final String rRollNo = intent.getStringExtra("roll_no");

        final ArrayList<String> list3 = new ArrayList<>();
        final ArrayAdapter adapter3 = new ArrayAdapter<String>(this,R.layout.list_item,list3);
        mListView.setAdapter(adapter3);

        DatabaseReference reference3 = FirebaseDatabase.getInstance().getReference().child("Messages");
        reference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Information info = snapshot.getValue(Information.class);
                    String check = info.getFrom();
                    String check2 = info.getTo();
                    String txt = "From : " + info.getFrom() + "\n" + "To : " + info.getTo() + "\n" + "Message : " + info.getMessage();
                    if(check.equals(rFaEmail) || check2.equals(rFaEmail)){
                        list3.add(txt);
                    }

                }
                adapter3.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
