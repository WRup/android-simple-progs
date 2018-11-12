package com.example.wrup.lab04.model.dao;


import com.example.wrup.lab04.model.dbTables.StudentGroupTable;
import com.example.wrup.lab04.model.dbTables.StudentTable;
import com.example.wrup.lab04.model.dbTables.GroupTable;
import com.example.wrup.lab04.model.iDao.IDaoRead;
import com.example.wrup.lab04.model.objects.Group;
import com.example.wrup.lab04.model.objects.Student;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class StudentDaoRead implements IDaoRead {

    private SQLiteDatabase db;

    public StudentDaoRead(SQLiteDatabase db) { this.db = db; }

    public Student findById(int student_id) {

        String query = "SELECT * FROM " + StudentTable.TABLE_NAME + " WHERE " +
                StudentTable.StudentColumns.ID + " = " + student_id + ";";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        if(c.getCount() >= 1){
            throw new RuntimeException("TOO MANY ROWS EXCEPTION");
        }
        else if(c.getCount() <= 0){
            throw new RuntimeException("NO DATA FOUND EXCEPTION");
        }
        int id = c.getInt(c.getColumnIndex(StudentTable.StudentColumns.ID));
        String name = c.getString(c.getColumnIndex(StudentTable.StudentColumns.NAME));
        String surname = c.getString(c.getColumnIndex(StudentTable.StudentColumns.SURNAME));

        GroupDaoRead groupDao = new GroupDaoRead(db);
        String query2 = "SELECT * FROM " + StudentGroupTable.TABLE_NAME + " WHERE "
                + StudentGroupTable.StudentGroupColumns.STUDENT_ID + "="
                + id + ";";

        ArrayList<Group> groups = new ArrayList<>();
        Cursor c2 = db.rawQuery(query2, null);
        if(c2.getCount() <= 0){
            throw new RuntimeException("NO DATA FOUND EXCEPTION");
        }
        if (c2.getCount() > 0) {
            c2.moveToFirst();
            do {
                int groupId = c2.getInt(c2.getColumnIndex(GroupTable.GroupColumns.ID.toString()));
                Group group = groupDao.findById(groupId);
                groups.add(group);
            } while (c2.moveToNext());
        }

        Student student = new Student(name, surname, groups);
        student.setId(id);
        c.close();
        c2.close();
        return student;
    }

    @Override
    public ArrayList<Student> findAll() {
        ArrayList<Student> students = new ArrayList<>();
        String query = "SELECT * FROM " + StudentTable.TABLE_NAME + ";";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        if(c.getCount() <= 0){
            students.clear();
            c.close();
            return students;
        }

        do {
            int id = c.getInt(c.getColumnIndex(StudentTable.StudentColumns.ID.toString()));
            String name = c.getString(c.getColumnIndex(StudentTable.StudentColumns.NAME));
            String surname = c.getString(c.getColumnIndex(StudentTable.StudentColumns.SURNAME));
            Student student = new Student(name, surname, null);
            student.setId(id);
            students.add(student);
        } while (c.moveToNext());

        c.close();
        return students;
    }

    @Override
    public ArrayList findAllByType(String type) {
        return null;
    }
}
