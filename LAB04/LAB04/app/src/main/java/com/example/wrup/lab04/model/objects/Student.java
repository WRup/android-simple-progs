package com.example.wrup.lab04.model.objects;

import java.util.ArrayList;
import java.util.Collections;


public class Student {
    private int id;
    private String name;
    private String surname;
    private ArrayList<Group> groups;

    public Student(String name, String surname, ArrayList<Group> groups) {
        this.name = name;
        this.surname = surname;
        this.groups = groups != null ? groups : new ArrayList<>(Collections.<Group>emptyList());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public String toString() {
        return name + ' ' + surname ;
    }
}
