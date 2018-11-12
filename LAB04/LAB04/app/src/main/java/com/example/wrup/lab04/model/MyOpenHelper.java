package com.example.wrup.lab04.model;

import com.example.wrup.lab04.model.dao.GroupDaoWrite;
import com.example.wrup.lab04.model.dao.StudentDaoWrite;
import com.example.wrup.lab04.model.dbTables.StudentTable;
import com.example.wrup.lab04.model.dbTables.GroupTable;
import com.example.wrup.lab04.model.dbTables.StudentGroupTable;
import com.example.wrup.lab04.model.objects.Student;
import com.example.wrup.lab04.model.objects.Group;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;


public class MyOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "sqliteApp.db";

    private StudentDaoWrite studDaoWrite;
    private GroupDaoWrite groupDaoWrite;

    private SQLiteDatabase db;

    public MyOpenHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        studDaoWrite = new StudentDaoWrite(db);
        groupDaoWrite = new GroupDaoWrite(db);
        createTables();
        insertIntoTables();
    }

    //????
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + StudentTable.TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + GroupTable.TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + StudentGroupTable.TABLE_NAME + ";");
        onCreate(db);
    }

    private void createTables() {
        String queryCreateStudentTable = "CREATE TABLE " +
                StudentTable.TABLE_NAME + "(" +
                StudentTable.StudentColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                StudentTable.StudentColumns.NAME + " TEXT, " +
                StudentTable.StudentColumns.SURNAME + " TEXT" +
                ");";

        String queryCreateGroupTable = "CREATE TABLE " +
                GroupTable.TABLE_NAME + "(" +
                GroupTable.GroupColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                GroupTable.GroupColumns.NAME + " TEXT," +
                GroupTable.GroupColumns.TYPE + " TEXT" +
                ");";

        String queryCreateStudentGroupTable = "CREATE TABLE " +
                StudentGroupTable.TABLE_NAME + "(" +
                StudentGroupTable.StudentGroupColumns.STUDENT_ID + " INTEGER, " +
                StudentGroupTable.StudentGroupColumns.GROUP_ID + " INTEGER," +

                "FOREIGN KEY (" + StudentGroupTable.StudentGroupColumns.GROUP_ID +
                ") REFERENCES " + GroupTable.TABLE_NAME + "(" +
                GroupTable.GroupColumns.ID + "), " +

                "FOREIGN KEY (" + StudentGroupTable.StudentGroupColumns.STUDENT_ID +
                ") REFERENCES " + StudentTable.TABLE_NAME + "(" +
                GroupTable.GroupColumns.ID + ") " +
                ");";

        db.execSQL(queryCreateStudentTable);
        db.execSQL(queryCreateGroupTable);
        db.execSQL(queryCreateStudentGroupTable);
    }

    private void insertIntoTables() {

        Group labgr1 = new Group("01","Laboratory");
        Group labgr2 = new Group("02","Laboratory");
        Group labgr3 = new Group("03","Laboratory");
        Group labgr4 = new Group("04","Laboratory");
        Group labgr5 = new Group("05","Laboratory");
        Group labgr6 = new Group("06","Laboratory");
        Group prgr1 = new Group("01", "Practice");
        Group prgr2 = new Group("02", "Practice");
        Group prgr3 = new Group("03", "Practice");
        Group legr1 = new Group("01", "Lecture");


        groupDaoWrite.insert(labgr1);
        groupDaoWrite.insert(labgr2);
        groupDaoWrite.insert(labgr3);
        groupDaoWrite.insert(labgr4);
        groupDaoWrite.insert(labgr5);
        groupDaoWrite.insert(labgr6);
        groupDaoWrite.insert(prgr1);
        groupDaoWrite.insert(prgr2);
        groupDaoWrite.insert(prgr3);
        groupDaoWrite.insert(legr1);

        Student newStudent1 = new Student("Jan", "Kowalski",
                new ArrayList<>(Arrays.asList(labgr1,prgr1, legr1)));
        Student newStudent2 = new Student("Maria", "Kowalska",
                new ArrayList<>(Arrays.asList(labgr2, prgr1, legr1)));
        Student newStudent3 = new Student("Patryk", "Papryka",
                new ArrayList<>(Arrays.asList(labgr3, prgr2, legr1)));
        Student newStudent4 = new Student("Pazury", "Cezara",
                new ArrayList<>(Arrays.asList(labgr4, prgr2, legr1)));

        studDaoWrite.insert(newStudent1);
        studDaoWrite.insert(newStudent2);
        studDaoWrite.insert(newStudent3);
        studDaoWrite.insert(newStudent4);
    }
}
