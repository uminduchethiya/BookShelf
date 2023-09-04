package com.example.bookshelf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bookshelf.databinding.ActivityAdminPanelBinding;
import com.example.bookshelf.databinding.ActivityRegistrationBinding;

public class AdminPanel extends AppCompatActivity {
ActivityAdminPanelBinding binding;
DBhelper databasehelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAdminPanelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databasehelper=new DBhelper(this);
        binding.addbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Admin_addBook.class);
                startActivity(intent);
                finish();
            }
        });
        binding.previewBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Adminpanel_previewBook.class);
                startActivity(intent);
                finish();
            }
        });






    }


}