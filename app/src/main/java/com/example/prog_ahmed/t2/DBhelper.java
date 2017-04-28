package com.example.prog_ahmed.t2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

/**
 * Created by Prog_AhMeD on 2017/04/28.
 */

public class DBhelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Students.db";
    public static final String TABLE_NAME="student_table";
    public static final String COL_1="ID";
    public static final String COL_2="NAME";
    public static final String COL_3="EMAIL";
    public static final String COL_4="MARKS";
    public static final String COL_5="PASSWORD";


    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        if (db.isOpen()) {
            db.execSQL("CREATE TABLE " + DATABASE_NAME + "." + TABLE_NAME + " (ID INTEGER PRIMARY KEY NOT NULL AUTOINCREMENT," +
                    "NAME TEXT NOT NULL,EMAIL TEXT NOT NULL,MARKS INTEGER NOT NULL,PASSWORD TEXT NOT NULL);");

        }

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name,String email,String marks,String pass){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COL_2,name);
        cv.put(COL_3,email);
        cv.put(COL_4,marks);
        cv.put(COL_5,pass);
        long res=db.insert(TABLE_NAME,null,cv);
        if(res == -1)
            return  false;
        else
            return true;
    }
    public Cursor getAll(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
    return res;
    }
    public boolean UpdateData(String id,String name,String email,String marks,String pass) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COL_1,id);
        cv.put(COL_2,name);
        cv.put(COL_3,email);
        cv.put(COL_4,marks);
        cv.put(COL_5,pass);
       db.update(TABLE_NAME,cv,"ID = ?",new String[] { id });
       return true;
    }
    public Integer delete(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[] { id });
    }
    }
