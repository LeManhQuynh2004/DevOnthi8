package com.quynhlm.dev.devonthi8;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class StudentDao {
    Db_Helper dbHelper;

    public StudentDao(Context context) {
        dbHelper = new Db_Helper(context);
    }

    public boolean insertData(Student student) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", student.getName());
        contentValues.put("birthday", student.getBirthday());
        contentValues.put("MSSV", student.getMSSV());
        long check = sqLiteDatabase.insert("Student", null, contentValues);
        student.setId((int) check);
        return check > 0;
    }

    public boolean deleteData(Student student) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String dk[] = {String.valueOf(student.getId())};
        long check = sqLiteDatabase.delete("Student", "id=?", dk);
        return check > 0;
    }

    public boolean updateData(Student student) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String dk[] = {String.valueOf(student.getId())};
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", student.getName());
        contentValues.put("birthday", student.getBirthday());
        contentValues.put("MSSV", student.getMSSV());
        long check = sqLiteDatabase.update("Student", contentValues, "id=?", dk);
        return check > 0;
    }

    public ArrayList<Student> selectAll() {
        ArrayList<Student> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Student", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new Student(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3)));
            } while (cursor.moveToNext());
        }
        return list;
    }
}
