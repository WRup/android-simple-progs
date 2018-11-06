package com.example.student.myapplication;

import java.util.ArrayList;

public class StudentList {

    private ArrayList<Student> studentList;

    public StudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }
}
