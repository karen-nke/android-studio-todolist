package com.bignerdranch.android.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.criminalintent.database.ToDoBaseHelper;
import com.bignerdranch.android.criminalintent.database.ToDoCursorWrapper;
import com.bignerdranch.android.criminalintent.database.ToDoDbSchema.ToDoTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ToDoLab {

    private static ToDoLab sToDoLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static ToDoLab get(Context context){
        if (sToDoLab ==null){
            sToDoLab = new ToDoLab(context);
        }
        return sToDoLab;
    }


    private ToDoLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase= new ToDoBaseHelper(mContext).getWritableDatabase();
    }

    public void addToDo( ToDo c){
        ContentValues values = getContentValues(c);
        mDatabase.insert(ToDoTable.NAME, null, values);
    }

    public List<ToDo> getToDos(){
        List<ToDo> toDos = new ArrayList<>();

        ToDoCursorWrapper cursor = (ToDoCursorWrapper) queryToDos(null, null);

        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                toDos.add(cursor.getToDo());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return toDos;
    }

    public ToDo getToDo(UUID id){

        ToDoCursorWrapper cursor = (ToDoCursorWrapper) queryToDos(ToDoTable.Cols.UUID + " = ? " , new String[]{id.toString()});

        try{
            if(cursor.getCount()==0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getToDo();
        }finally {
            cursor.close();
        }
    }

    public void updateToDo(ToDo toDo){
        String uuidString = toDo.getId().toString();
        ContentValues values = getContentValues(toDo);

        mDatabase.update(ToDoTable.NAME, values, ToDoTable.Cols.UUID + " = ? ", new String[]{uuidString});

    }

    private CursorWrapper queryToDos(String whereClause, String[] whereArgs){
        Cursor cursor= mDatabase.query(
                ToDoTable.NAME,
                null, //columns-null selects all columns
                whereClause,
                whereArgs,
                null,//groupBy
                null,//having
                null//orderBY
        );
        return new ToDoCursorWrapper(cursor);
    }
    private static ContentValues getContentValues(ToDo toDo){
        ContentValues values = new ContentValues();
        values.put(ToDoTable.Cols.UUID, toDo.getId().toString());
        values.put(ToDoTable.Cols.TITLE, toDo.getTitle());
        values.put(ToDoTable.Cols.DATE, toDo.getDate().getTime());
        values.put(ToDoTable.Cols.SOLVED, toDo.isSolved() ? 1 : 0);
        values.put(ToDoTable.Cols.COLLAB, toDo.getCollab());

        values.put(ToDoTable.Cols.DETAILS, toDo.getDetail());
        values.put(ToDoTable.Cols.CAT, toDo.getCat());
        return values;
    }

}
