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

public class MessageFromWardenActivity extends AppCompatActivity {

    private ListView listview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_from_warden);

        listview2 = (ListView) findViewById(R.id.listView2);

        Intent intent = getIntent();
        final String bParentName = intent.getStringExtra("parent_name");
        final String bStudentName = intent.getStringExtra("student_name");
        final String bFrom = intent.getStringExtra("email");
        final String bto = intent.getStringExtra("fa_email");
        final String bWardenEmail = intent.getStringExtra("warden_email");
        final String bRollNo = intent.getStringExtra("roll_no");


        final ArrayList<String> list2 = new ArrayList<>();
        final ArrayAdapter adapter2 = new ArrayAdapter<String>(this,R.layout.list_item2,list2);
        listview2.setAdapter(adapter2);

        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("Messages");
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Information info2 = snapshot.getValue(Information.class);
                    String check1 = info2.getFrom();
                    String check2 = info2.getTo();
                    String txt = "From : " + info2.getFrom() + "\n" + "To : " + info2.getTo() + "\n" + "Message : " + info2.getMessage();
                    if(check1.equals(bFrom) && check2.equals(bWardenEmail)){
                        list2.add(txt);
                   }

                }
                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
