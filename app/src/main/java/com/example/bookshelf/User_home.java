package com.example.bookshelf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class User_home extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    userDataadpter userDataadpter;
    private ImageButton btnHome, btnCategory, btnCart, btnProfile,btnMap;

    TextView Backtodashboard;
    ArrayList<DataClass> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        btnMap= findViewById(R.id.btnMap);
        btnCategory=findViewById(R.id.btnCategory);

        recyclerView = findViewById(R.id.userBooklist);
        database = FirebaseDatabase.getInstance().getReference("Books");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        userDataadpter = new userDataadpter(this, list);
        recyclerView.setAdapter(userDataadpter);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DataClass dataClass = dataSnapshot.getValue(DataClass.class);
                    list.add(dataClass);

                    dataClass.setKey(dataSnapshot.getKey());
                }
                userDataadpter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentmap =new Intent(User_home.this, MapsActivity.class);
                startActivity(intentmap);
            }
        });
        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(User_home.this, categories_activity.class);
                startActivity(intent);
            }
        });


    }
}