package com.example.bookshelf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class categories_activity extends AppCompatActivity {
    private ImageButton btnHome, btnCategory, btnCart, btnProfile, btnMaps;
    private LinearLayout categories1, categories2, categories3, categories4, categories5, categories6; // Add similar variables for other categories

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // Initialize views
        btnHome = findViewById(R.id.btnHome);
        btnCategory = findViewById(R.id.btnCategory);
        btnCart = findViewById(R.id.btnCart);
        btnProfile = findViewById(R.id.btnProfile);
        btnMaps = findViewById(R.id.btnMap);
        // Initialize category layouts
        categories1 = findViewById(R.id.Category_1);
        categories2 = findViewById(R.id.Category_2);
        categories3 = findViewById(R.id.Category_3);
        categories4 = findViewById(R.id.Category_4);
        categories5 = findViewById(R.id.Category_5);
        categories6 = findViewById(R.id.Category_6);
        // Add similar initialization for other categories

        // Set up button clicks for navigation
//        btnCategory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Refresh the activity (reload books or perform other actions)
//                finish();
//                startActivity(getIntent());
//            }
//        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentmap =new Intent(categories_activity.this, User_home.class);
                startActivity(intentmap);
            }
        });
        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentmap =new Intent(categories_activity.this, MapsActivity.class);
                startActivity(intentmap);
            }
        });


//        btnCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v1) {
//                // Navigate to CartActivity
//                startActivity(new Intent(categories_activity.this, User_home.class));
//            }
//        });

//        btnProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v2) {
//                // Navigate to ProfileActivity
//                startActivity(new Intent(categories_activity.this, User_home.class));
//            }
//        });


        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v4) {
                // Navigate to MapActivity
                Intent intent = new Intent(categories_activity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        // Set up click listeners for category layouts
        categories1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v3) {
                // When Category 1 is clicked, send its text to CategoryLoadActivity
                Intent intent = new Intent(categories_activity.this, categeory_view.class);
                intent.putExtra("categories1", "Romance"); // Change "Romance" to the actual category name
                startActivity(intent);
            }
        });
//        categories2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v3) {
//                // When Category 1 is clicked, send its text to CategoryLoadActivity
//                Intent intent = new Intent(categories_activity.this, categeory_view.class);
//                intent.putExtra("categories2", "Education"); // Change "Romance" to the actual category name
//                startActivity(intent);
//            }
//        });
//        categories3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v3) {
//                // When Category 1 is clicked, send its text to CategoryLoadActivity
//                Intent intent = new Intent(categories_activity.this, categeory_view.class);
//                intent.putExtra("categories3", "Mystry"); // Change "Romance" to the actual category name
//                startActivity(intent);
//            }
//        });
//        categories4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v3) {
//                // When Category 1 is clicked, send its text to CategoryLoadActivity
//                Intent intent = new Intent(categories_activity.this, categeory_view.class);
//                intent.putExtra("categories4", "Children"); // Change "Romance" to the actual category name
//                startActivity(intent);
//            }
//        });
//        categories5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v3) {
//                // When Category 1 is clicked, send its text to CategoryLoadActivity
//                Intent intent = new Intent(categories_activity.this, categeory_view.class);
//                intent.putExtra("categories5", "Fantasy"); // Change "Romance" to the actual category name
//                startActivity(intent);
//            }
//        });
//        categories6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v3) {
//                // When Category 1 is clicked, send its text to CategoryLoadActivity
//                Intent intent = new Intent(categories_activity.this, categeory_view.class);
//                intent.putExtra("categories6", "Advanture"); // Change "Romance" to the actual category name
//                startActivity(intent);
//            }
//        });
        // Add similar click listeners for other categories
    }
}