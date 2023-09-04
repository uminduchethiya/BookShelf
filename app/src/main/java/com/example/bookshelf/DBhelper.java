package com.example.bookshelf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayInputStream;

public class DBhelper extends SQLiteOpenHelper {
    //Create database
    private static final String db_name="Bookshop";
    //database version
    private  static  final int db_version=1;



    public DBhelper( Context context) {
        super(context,db_name,null,db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //sql statement
        String sql="CREATE TABLE user(UserName TEXT,"+ "Email TEXT,"+"Password,"+"UserRole TEXT)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "user");
        onCreate(db);
    }

    public  Boolean insertData(String UserName,String Email,String Password,String UserRole){
        SQLiteDatabase sqLiteDatabase =this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("UserName",UserName);
        contentValues.put("Email",Email);
        contentValues.put("Password",Password);
        contentValues.put("UserRole",UserRole);

        long result=sqLiteDatabase.insert("user",null,contentValues);
        if (result==-1){
            return false;
        }
        else {
            return true;
        }
    }




    public Boolean checkEmail(String Email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from user Where Email = ? ",new String[]{Email});
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count > 0 )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String checkUserRole(String Email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select UserRole from user Where Email = ? ",new String[]{Email});
        if (cursor.moveToFirst())
        {
            int userRoleColumnIndex = cursor.getColumnIndex("UserRole");
            // Retrieve the user role from the cursor
            String UserRole = cursor.getString(userRoleColumnIndex);
            cursor.close();
            db.close();
            return UserRole;
        }
        else
        {
            cursor.close();
            db.close();
            return null; // Or any other appropriate value indicating the user doesn't exist
        }

    }


    public boolean CheckCredentials(String Email, String Password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from user Where Email = ? and Password = ?",new String[]{Email,Password});
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }




}
