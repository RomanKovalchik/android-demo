package com.example.demoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {

        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create Table Userdetails(name TEXT primary key," +
            "contact TEXT, dbo TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     db.execSQL("drop Table if exists Userdetails");
    }

    public boolean insertuserdata (String name, String contact, String dob){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        contentValues.put("dbo", dob);
        long result = db.insert("Userdetails", null, contentValues);
       //  long data = result;
        if (result > 0){
            return true;
        } else {
            return false;
        }

    }

    public boolean updateuserdata (String name, String contact, String dob){


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact", contact);
        contentValues.put("dbo", dob);
        Cursor cursor = db.rawQuery("Select * from Userdetails where name = ?",
                new String[]{name});
        if(cursor.getCount()>0) {
            long result = db.update("Userdetails", contentValues, "name=?",
                    new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    public boolean deletedata (String name){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Userdetails where name = ?",
                new String[]{name});
        if(cursor.getCount()>0) {
            long result = db.delete("Userdetails", "name=?",
                    new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata () {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Userdetails",
                null);
      return cursor;
    }

}
