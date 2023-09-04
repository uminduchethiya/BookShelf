package com.example.bookshelf;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataAdpter extends RecyclerView.Adapter<DataAdpter.MyViewHolder> {

    Context context;
    ArrayList<DataClass> list;

    public DataAdpter(Context context, ArrayList<DataClass> list) {
        this.context = context;
        this.list = list;
    }

    @androidx.annotation.NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
       return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull MyViewHolder holder, int position) {
        DataClass dc=list.get(position);

        holder.title.setText((dc.getBookTitle()));
        holder.isbn.setText((dc.getBookIsbn()));
        holder.author.setText((dc.getBookAuthor()));
        holder.price.setText((dc.getBookprice()));
        holder.quntity.setText(dc.getBookquntity());
        holder.description.setText((dc.getBookdescription()));
        Glide.with(context).load(dc.getBookImage()).into(holder.image);

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus= DialogPlus.newDialog(holder.isbn.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,2000)
                        .create();
//                dialogPlus.show();

                View view =dialogPlus.getHolderView();
                EditText Title=view.findViewById(R.id.txttitle);
                EditText  Author=view.findViewById(R.id.txtauthor);
                EditText isbn=view.findViewById(R.id.txtisbn);
                EditText price=view.findViewById(R.id.txtprice);
                EditText quntity=view.findViewById(R.id.txtquntity);
                EditText description=view.findViewById(R.id.txtdiscription);
                 Button btnUpdate=view.findViewById(R.id.btnupdate);

                Title.setText(dc.getBookTitle());
                Author.setText(dc.getBookAuthor());
                isbn.setText(dc.getBookIsbn());
                price.setText(dc.getBookprice());
                quntity.setText(dc.getBookquntity());
                description.setText(dc.getBookdescription());
                dialogPlus.show();
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("bookTitle",Title.getText().toString());
                        map.put("bookAuthor",Author.getText().toString());
                        map.put("bookIsbn",isbn.getText().toString());
                        map.put("bookprice",price.getText().toString());
                        map.put("bookdescription",description.getText().toString());
                        map.put("bookquntity",quntity.getText().toString());

                        // Update the data in Firebase
                        FirebaseDatabase.getInstance().getReference().child("Books")
                                .child(dc.getKey()) // Assuming you have a getKey method in your DataClass
                                .updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.title.getContext(), "Suceesfully Updated", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure( Exception e) {
                                        Toast.makeText(holder.title.getContext(), "Error Wile Updated", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });




            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.isbn.getContext());
                builder.setTitle("Are You Sure");
                builder.setMessage("Deleted data cant be undo");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Books").child(dc.getKey());
                        databaseReference.removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.isbn.getContext(),"Cancelled",Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView title,isbn,author,price,quntity,description;
        ImageView image;

        Button btnEdit,btnDelete;
        public  MyViewHolder(@NonNull View itemView){
            super(itemView);
             title=itemView.findViewById(R.id.recTitle);
             isbn=itemView.findViewById(R.id.recisbn);
             author=itemView.findViewById(R.id.recauthor);
             image=itemView.findViewById(R.id.recImage);
             price=itemView.findViewById(R.id.recprice);
             quntity=itemView.findViewById(R.id.recquntity);
             description=itemView.findViewById(R.id.recdescription);

             btnEdit=itemView.findViewById(R.id.btnedit);
             btnDelete=itemView.findViewById(R.id.btndelete);


        }
    }
}
