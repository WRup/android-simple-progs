package com.example.wrup.lab04.controler;

import com.example.wrup.lab04.model.dao.GroupDaoWrite;
import com.example.wrup.lab04.model.dao.StudentDaoWrite;
import com.example.wrup.lab04.model.dao.GroupDaoRead;
import com.example.wrup.lab04.model.dao.StudentDaoRead;
import com.example.wrup.lab04.model.MyOpenHelper;
import com.example.wrup.lab04.model.objects.Student;
import com.example.wrup.lab04.model.objects.Group;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DbManager {

    private SQLiteDatabase db;
    private Context context;
    private GroupDaoWrite groupDaoWrite;
    private StudentDaoWrite studentDaoWrite;
    private GroupDaoRead groupDaoRead;
    private StudentDaoRead studentDaoRead;

    public DbManager(Context context){
        this.context = context;
        SQLiteOpenHelper sqLiteOpenHelper = new MyOpenHelper(context);
        this.db = sqLiteOpenHelper.getWritableDatabase();

        groupDaoWrite = new GroupDaoWrite(db);
        studentDaoWrite = new StudentDaoWrite(db);
        groupDaoRead = new GroupDaoRead(db);
        studentDaoRead = new StudentDaoRead(db);
    }

    //Student DAO
    public ArrayList<Student> getStudents() {
        return studentDaoRead.findAll();
    }

    public Student getStudent(int id) {
        return studentDaoRead.findById(id);
    }

    public void insertStudent(Student student) { studentDaoWrite.insert(student);  }

    public void updateStudent(Student student) { studentDaoWrite.update(student);  }

    public void deleteStudent(int studentId) {
        studentDaoWrite.delete(studentId);
    }

    //Groups DAO
    public ArrayList<Group> getGroupsByType(String type) {
        return groupDaoRead.findAllByType(type);
    }
    public ArrayList<Group> getGroups() {
        return groupDaoRead.findAll();
    }

    public Group getGroup(int id) {
        return groupDaoRead.findById(id);
    }

    public void insertGroup(Group group) { groupDaoWrite.insert(group); }

    public void deleteGroup(int groupId) {
        groupDaoWrite.delete(groupId);
    }
}

