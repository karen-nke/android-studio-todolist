package com.bignerdranch.android.criminalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.criminalintent.ToDo;

import java.util.Date;
import java.util.UUID;

public class ToDoCursorWrapper extends CursorWrapper{
    public ToDoCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public ToDo getToDo(){
        String uuidString = getString(getColumnIndex(ToDoDbSchema.ToDoTable.Cols.UUID));
        String title = getString(getColumnIndex(ToDoDbSchema.ToDoTable.Cols.TITLE));
        long date = getLong(getColumnIndex(ToDoDbSchema.ToDoTable.Cols.DATE));
        int isSolved = getInt(getColumnIndex(ToDoDbSchema.ToDoTable.Cols.SOLVED));
        String collab = getString(getColumnIndex(ToDoDbSchema.ToDoTable.Cols.COLLAB));
        String details = getString(getColumnIndex(ToDoDbSchema.ToDoTable.Cols.DETAILS));
        String cat = getString(getColumnIndex(ToDoDbSchema.ToDoTable.Cols.CAT));


        ToDo toDo = new ToDo(UUID.fromString(uuidString));
        toDo.setTitle(title);
        toDo.setDate(new Date(date));
        toDo.setSolved(isSolved != 0);
        toDo.setCollab(collab);
        toDo.setDetail(details);
        toDo.setCat(cat);

        return toDo;

    }

}
