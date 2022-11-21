package com.example.db1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.service.autofill.AutofillService;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {

    private static final String dbname = "signup.db";
    public
    database(@Nullable Context context) {
        super(context, dbname, null,  1);
    }

    @Override
    public
    void onCreate(SQLiteDatabase db) {
        String q = "create table user ( id integer primary key autoincrement,name text, username text, password text )";
        db.execSQL(q);

    }

    @Override
    public
    void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public
    boolean insert_data(String name, String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("name",name);
        c.put("username", username);
        c.put("password", password);

        long r = db.insert("user",null,c);
        if(r==-1)
            return false;
        else
            return true;
    }

    public boolean delete_data(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user where username=?", new String[]{username});
        if (cursor.getCount() > 0) {
            long r = db.delete("user", "username=?", new String[]{username});
            if (r == -1)
                return false;
            else
                return true;
        }
    else return  false;

    }

    public  boolean update_data(String name, String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("name",name);
        c.put("password",password);

        //String whereClause = "id = name";
        //String whereArgs[] = {username..toString()};
      //  db.update("Items", c, whereClause, whereArgs);

        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE useername = ?",new String[]{username});
        if(cursor.getCount()>0) {
            long r = db.update("user", c, "username = ?", new String[]{username});
            if (r == -1)
                return false;
            else
                return true;
        }
        return false;
    }


    public
    Cursor getinfo(){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM user " ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;

    }
}
