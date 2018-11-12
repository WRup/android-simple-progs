package com.example.wrup.lab04.model.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.wrup.lab04.model.dbTables.StudentGroupTable;
import com.example.wrup.lab04.model.dbTables.StudentTable;
import com.example.wrup.lab04.model.iDao.IDaoWrite;
import com.example.wrup.lab04.model.objects.Group;
import com.example.wrup.lab04.model.objects.Student;

import java.util.ArrayList;


public class StudentDaoWrite implements IDaoWrite<Student> {

    SQLiteDatabase db;

    public StudentDaoWrite(SQLiteDatabase db){ this.db = db; }

    @Override
    public void insert(Student student) {
        ContentValues values = new ContentValues();

        values.put(StudentTable.StudentColumns.NAME, student.getName());
        values.put(StudentTable.StudentColumns.SURNAME, student.getSurname());
        int id = (int) db.insert(StudentTable.TABLE_NAME, null, values);
        student.setId(id);

        this.InsertGroups(student);
    }

    @Override
    public void update(Student student) {
        ContentValues values = new ContentValues();

        values.put(StudentTable.StudentColumns.NAME, student.getName());
        values.put(StudentTable.StudentColumns.SURNAME, student.getSurname());
        db.update(StudentTable.TABLE_NAME, values, StudentTable.StudentColumns.ID + "=" + student.getId(), null);

        db.delete(StudentGroupTable.TABLE_NAME, StudentGroupTable.StudentGroupColumns.STUDENT_ID + "=" + student.getId(), null);
        this.InsertGroups(student);
    }

    @Override
    public void delete(int id) {
        db.delete(StudentGroupTable.TABLE_NAME, StudentGroupTable.StudentGroupColumns.STUDENT_ID + "=" + id, null);
        db.delete(StudentTable.TABLE_NAME, StudentTable.StudentColumns.ID + "=" + id, null);
    }

    private void InsertGroups(Student student) {
        ArrayList<Group> groups = student.getGroups();
        for (Group group : groups) {
            ContentValues studentGroupValues = new ContentValues();
            studentGroupValues.put(StudentGroupTable.StudentGroupColumns.STUDENT_ID, student.getId());
            studentGroupValues.put(StudentGroupTable.StudentGroupColumns.GROUP_ID, group.getId());
            db.insert(StudentGroupTable.TABLE_NAME, null, studentGroupValues);
        }
    }
}
