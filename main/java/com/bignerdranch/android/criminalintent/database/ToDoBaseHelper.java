package com.bignerdranch.android.criminalintent.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bignerdranch.android.criminalintent.database.ToDoDbSchema.ToDoTable;

public class ToDoBaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME= "todoBase.db";
    private static final int VERSION= 1;

    public ToDoBaseHelper(Context context){
        super(context, DATABASE_NAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + ToDoTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                ToDoTable.Cols.UUID + ", " +
                ToDoTable.Cols.TITLE + ", " +
                ToDoTable.Cols.DATE + ", "+
                ToDoTable.Cols.SOLVED + ","+
                ToDoTable.Cols.DETAILS + ","+
                ToDoTable.Cols.CAT + ","+
                ToDoTable.Cols.COLLAB +
                ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion){

    }

}
