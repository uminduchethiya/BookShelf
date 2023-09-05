package com.example.bookshelf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class map_implement extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        ImageButton btnHome = findViewById(R.id.btnHome);
        ImageButton btnCategory = findViewById(R.id.btnCategory);
        ImageButton btnCart = findViewById(R.id.btnCart);
        ImageButton btnMap = findViewById(R.id.btnMap);
        ImageButton btnProfile = findViewById(R.id.btnProfile);

        // Set click listeners for the buttons
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the user_home activity
                Intent intent = new Intent(map_implement.this, User_home.class);
                startActivity(intent);
            }
        });
    }}

//        btnCategory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Navigate to the categories activity
//                Intent intent = new Intent(map_implement.this, categories_activity.class);
//                startActivity(intent);
//            }
//        });
//
//        btnCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Navigate to the cart activity
//                Intent intent = new Intent(map_implement.this, cartactivity.class);
//                startActivity(intent);
//            }
//        });
//
//        btnMap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Refresh the current activity (MainActivity)
//                finish();
//                startActivity(getIntent());
//            }
//        });
//
//        btnProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Navigate to the profile activity
//                Intent intent = new Intent(map_implement.this, profile_activity.class);
//                startActivity(intent);
//            }
//        });
//    }


