package com.example.bookshelf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookshelf.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {
    ActivityLoginBinding binding;
 DBhelper dBhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dBhelper = new DBhelper (this);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = binding.EEmail.getText().toString();
                String Password = binding.EPassword.getText().toString();

                if (Email.equals("") || Password.equals("")) {
                    Toast.makeText(Login.this, "Please Fill All The Fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (Email.equals("SuperAdmin") || Password.equals("SuperAdmin")) {
                        Intent intentSuperAdmin = new Intent(getApplicationContext(), AdminPanel.class);
                        startActivity(intentSuperAdmin);
                    } else {
                        Boolean CheckCredentailResult = dBhelper.CheckCredentials(Email, Password);
                        if (CheckCredentailResult == true)

                            Toast.makeText(Login.this, "Login Successfuly", Toast.LENGTH_SHORT).show();
                        String UserRoleResult = dBhelper.checkUserRole(Email);
                        if (UserRoleResult.equals("Admin")) {
                            Intent intentAdmin = new Intent(getApplicationContext(),AdminPanel.class);
                            startActivity(intentAdmin);
                        } else if (UserRoleResult.equals("Customer")) {
                            Intent intentCustomer = new Intent(getApplicationContext(),User_home.class);
                            startActivity(intentCustomer);
                        }

                        else
                    {
                        Toast.makeText(Login.this, "Login Failed.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
                    }



        });
        binding.RegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentregister=new Intent(getApplicationContext(), Registration.class);
                startActivity(intentregister);
            }
        });

    }
}