package com.example.wrup.lab04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.wrup.lab04.controler.DbManager;
import com.example.wrup.lab04.model.objects.Group;
import com.example.wrup.lab04.model.objects.Student;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StudentGroupsActivity extends AppCompatActivity {

    private int studentId;
    private ArrayList<Group> groupList = new ArrayList<>();
    private ArrayAdapter<Group> adapter;
    private ListView list;
    private DbManager dbManager;
    private EditText nameTextview;
    private EditText surnameTextview;
    private Button changeGroupBtn;
    boolean editEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_groups);
        dbManager = new DbManager(this);
        Bundle extras = getIntent().getExtras();
        list = (ListView) findViewById(R.id.groupsList);
        studentId =  extras.getInt("studentId");
        changeGroupBtn = (Button) findViewById(R.id.change_group_btn);

        nameTextview = (EditText)findViewById(R.id.name_edittext);
        surnameTextview = (EditText)findViewById(R.id.surname_edittext);
        nameTextview.setEnabled(false);
        surnameTextview.setEnabled(false);

        if (studentId > 0) {
            Student student = dbManager.getStudent(studentId);
            nameTextview.setText(student.getName());
            surnameTextview.setText(student.getSurname());
            groupList = student.getGroups();
            if(groupList.isEmpty())
                changeGroupBtn.setText("Add Groups");
        } else {
            nameTextview.setText("");
            surnameTextview.setText("");
            editClicked(findViewById(R.id.ebit_btn));
            groupList.clear();
            changeGroupBtn.setText("Add Groups");
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, groupList);
        list.setAdapter(adapter);
        list.requestFocus();
    }

    public void editClicked(View view) {
        Button btn = (Button) view;
        if (editEnabled) {
            ArrayList<Group> groups = new ArrayList<>();
            for (int i = 0; i < list.getCount(); ++i) {
                groups.add((Group)list.getItemAtPosition(i));
            }
            Student student = new Student(nameTextview.getText().toString(), surnameTextview.getText().toString(), groups);
            student.setId(this.studentId);
            if (this.studentId > 0) {
                dbManager.updateStudent(student);
            } else {
                dbManager.insertStudent(student);
            }
            Intent i = new Intent(StudentGroupsActivity.this, StudentListActivity.class);
            startActivity(i);
        } else {
            Button groupBtn = (Button)findViewById(R.id.change_group_btn);
            groupBtn.setVisibility(View.VISIBLE);
            nameTextview.setEnabled(true);
            surnameTextview.setEnabled(true);
            btn.setText("Save");
            editEnabled = true;
        }
    }

    public void groupListClicked(View view) {
        ArrayList<Integer> groupIds = new ArrayList<>();
        ArrayList<Group> tempGroups;
        if(studentId > 0)
            tempGroups = dbManager.getStudent(studentId).getGroups();
        else
            tempGroups = dbManager.getGroups();

        for(Group gr : tempGroups){
            groupIds.add(gr.getId());
        }

        Intent i = new Intent(StudentGroupsActivity.this, GroupListActivity.class);
        i.putExtra("groupids", groupIds);
        startActivityForResult(i, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                ArrayList<Integer> array = data.getIntegerArrayListExtra("newIds");

                adapter.clear();

                if (array != null) {
                    for (Integer idGroup : array) {
                        adapter.add(dbManager.getGroup(idGroup));
                    }
                }
            }
        }
    }
}
