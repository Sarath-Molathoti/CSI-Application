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

public class WardenViewMsgActivity extends AppCompatActivity {

    private ListView wListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warden_view_msg);

        wListView = (ListView) findViewById(R.id.listView4);

        Intent intent = getIntent();
       // final String tParentEmail = intent.getStringExtra("parent_email");
        final String tWardenEmail = intent.getStringExtra("w_email");
        final String tWardenName = intent.getStringExtra("w_name");
        final String tWardenUserId = intent.getStringExtra("w_user_id");
       // final String tRollNo = intent.getStringExtra("roll_no");

        final ArrayList<String> list4 = new ArrayList<>();
        final ArrayAdapter adapter4 = new ArrayAdapter<String>(this,R.layout.list_item,list4);
        wListView.setAdapter(adapter4);

        DatabaseReference reference4 = FirebaseDatabase.getInstance().getReference().child("Messages");
        reference4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Information info = snapshot.getValue(Information.class);
                    String check = info.getFrom();
                    String check2 = info.getTo();
                    String txt = "From : " + info.getFrom() + "\n" + "To : " + info.getTo() + "\n" + "Message : " + info.getMessage();
                    if(check.equals(tWardenEmail) || check2.equals(tWardenEmail)){
                        list4.add(txt);
                    }

                }
                adapter4.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
