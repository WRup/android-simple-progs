package com.example.student.myapplication;

public class Student {

    private String Name;
    private String Surname;

    public Student(String name, String surname) {
        Name = name;
        Surname = surname;
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    @Override
    public String toString() {
        return this.getName() + " " + this.getSurname();
    }
}
