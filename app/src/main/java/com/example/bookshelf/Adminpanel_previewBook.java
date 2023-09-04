package com.example.bookshelf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Adminpanel_previewBook extends AppCompatActivity {
RecyclerView recyclerView;
DatabaseReference database;
DataAdpter dataAdpter;

    TextView Backtodashboard;
ArrayList<DataClass> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpanel_preview_book);

        Backtodashboard=findViewById(R.id.backtodashboard);

        recyclerView=findViewById(R.id.booklist);
        database= FirebaseDatabase.getInstance().getReference("Books");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list =new ArrayList<>();
        dataAdpter =new DataAdpter(this,list);
        recyclerView.setAdapter(dataAdpter);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot :snapshot.getChildren()){
                    DataClass dataClass=dataSnapshot.getValue(DataClass.class);
                    list.add(dataClass);

                    dataClass.setKey(dataSnapshot.getKey());
                }
                dataAdpter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Backtodashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Adminpanel_previewBook.this, AdminPanel.class);
                startActivity(intent);
            }
        });



    }

}