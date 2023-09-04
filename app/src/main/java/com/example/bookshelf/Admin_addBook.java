package com.example.bookshelf;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookshelf.databinding.ActivityAdminAddBookBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Admin_addBook extends AppCompatActivity {

    ImageView uploadImages;
    Button addbook;
    EditText bookTitle, bookAuthor, bookisbn ,bookpPrice,bookDescription,bookQuntity;
    Spinner bookCategorie;
    String imgeURL;
    Uri uri;
    TextView Backtodashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_book);

        uploadImages = findViewById(R.id.uploadimage);
        bookCategorie = findViewById(R.id.rolecategories);
        bookTitle = findViewById(R.id.booktitle);
        bookAuthor = findViewById(R.id.bookauthor);
        bookisbn = findViewById(R.id.bookisbn);
        addbook = findViewById(R.id.btnaddbook);
        bookpPrice=findViewById(R.id.bookprice);
        bookQuntity=findViewById(R.id.bookquntity);
        bookDescription=findViewById(R.id.bookdescription);

        Backtodashboard=findViewById(R.id.backtodashboard);



        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            uri = data.getData();
                            uploadImages.setImageURI(uri);
                        } else {
                            Toast.makeText(Admin_addBook.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        uploadImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        addbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();

            }
        });
        Backtodashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_addBook.this, AdminPanel.class);
                startActivity(intent);
            }
        });




    }

    public void saveData() {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Book Images")
                .child(uri.getLastPathSegment());
        AlertDialog.Builder builder = new AlertDialog.Builder(Admin_addBook.this);
        builder.setCancelable(false);
        builder.setView(R.layout.activity_admin_add_book);
        AlertDialog dialog = builder.create();
        dialog.show();
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete()) ;
                Uri urlImage = uriTask.getResult();
                imgeURL = urlImage.toString();
                uploadData();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
    }

    public void uploadData() {
        String Category = bookCategorie.toString();
        String BookTtile = bookTitle.getText().toString();
        String BookAuthor = bookAuthor.getText().toString();
        String Bookisbn = bookisbn.getText().toString();
        String BookPrice= bookpPrice.getText().toString();
        String BookQuntity=bookQuntity.getText().toString();
        String BookDescription=bookDescription.getText().toString();



        DataClass dataClass = new DataClass(Category, BookTtile, BookAuthor, Bookisbn,imgeURL,BookPrice,BookQuntity,BookDescription );

        FirebaseDatabase.getInstance().getReference("Books").child(Bookisbn)
                .setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Admin_addBook.this, "Saved", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Admin_addBook.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });


    }


}






