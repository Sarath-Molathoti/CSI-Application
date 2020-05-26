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
import java.util.Collections;

public class ShowMsgsFromFaActivity extends AppCompatActivity {
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_msgs_from_fa);

        listview = (ListView) findViewById(R.id.listView);

        Intent intent = getIntent();
        final String qParentName = intent.getStringExtra("parent_name");
        final String qStudentName = intent.getStringExtra("student_name");
        final String from = intent.getStringExtra("email");
        final String qto = intent.getStringExtra("fa_email");
        final String qWardenEmail = intent.getStringExtra("warden_email");
        final String qRollNo = intent.getStringExtra("roll_no");

        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.list_item,list);
        listview.setAdapter(adapter);

        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("Messages");
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Information info = snapshot.getValue(Information.class);
                    String check = info.getFrom();
                    String check2 = info.getTo();
                    String txt = "From : " + info.getFrom() + "\n" + "To : " + info.getTo() + "\n" + "Message : " + info.getMessage();
                    if((check.equals(from) && check2.equals(qto)) || (check.equals(qto) && check2.equals(from))){
                        list.add(txt);
                   }

                }
                if (list.isEmpty()) {

                    list.add("                No Messages    ");
                }
                Collections.reverse(list);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
