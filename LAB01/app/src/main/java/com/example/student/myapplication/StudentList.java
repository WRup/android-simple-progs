package com.example.student.myapplication;

import java.util.ArrayList;

public class StudentList {

    private ArrayList<String> studentList;

    public StudentList(ArrayList<String> studentList) {
        this.studentList = studentList;
    }

    public ArrayList<String> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<String> studentList) {
        this.studentList = studentList;
    }
}
