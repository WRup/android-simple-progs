package com.example.student.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        StudentList studentList = new StudentList(createList());
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, studentList.getStudentList());
        ListView listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(itemsAdapter);
        for(int i=0; i<5;i++)
        itemsAdapter.add(studentList.getStudentList().get(i));

    }


    private ArrayList<String> createList(){
        Student s1 = new Student("Jan", "agsdgsd");
        Student s2 = new Student("Lam", "sgassgddsdgsd");
        Student s3 = new Student("Siam", "asdas");
        Student s4 = new Student("Ktos", "sgsdfgsdfgshfddsdgsd");
        Student s5 = new Student("Tam", "sdfh");

        ArrayList<String> studentList  = new ArrayList<>();
        studentList.add(s1.getName() +" "+ s5.getSurname());
        studentList.add(s2.getName() +" "+ s5.getSurname());
        studentList.add(s3.getName() +" "+ s5.getSurname());
        studentList.add(s4.getName() +" "+ s5.getSurname());
        studentList.add(s5.getName() +" "+ s5.getSurname());

        return studentList;

    }

}
