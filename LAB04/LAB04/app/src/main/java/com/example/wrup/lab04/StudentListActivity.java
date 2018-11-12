package com.example.wrup.lab04;

import com.example.wrup.lab04.model.objects.Student;
import com.example.wrup.lab04.controler.DbManager;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity {
    private ArrayList<Student> studentList = new ArrayList<>();
    private ArrayAdapter<Student> adapter;
    private ListView list;
    private DbManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        dbManager = new DbManager(this);

        list = (ListView) findViewById(R.id.studentList);
        fillTheList();
    }

    private void fillTheList() {
        studentList = dbManager.getStudents();
        this.setListItemListener();

        adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, studentList);
        list.setAdapter(adapter);
    }

    private void setListItemListener() {

        list.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Student st = (Student) parent.getItemAtPosition(position);
                    Intent i = new Intent(StudentListActivity.this, StudentGroupsActivity.class);
                    i.putExtra("studentId", st.getId());
                    startActivity(i);
                }
            }
        );

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View arg1, int position, long id) {
                final Student st = (Student) parent.getItemAtPosition(position);

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(StudentListActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_student_remove, null);
                Button btn = (Button) mView.findViewById(R.id.remove_student_button);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                   public void onClick(View view) {
                        dbManager.deleteStudent(st.getId());
                        Toast.makeText(StudentListActivity.this, R.string.student_removed_info, Toast.LENGTH_SHORT).show();
                        fillTheList();
                        dialog.dismiss();
                    }
                });
                dialog.show();
                return true;
            }
        });
    }

    public void newStudentClicked(View view) {
        Intent i = new Intent(StudentListActivity.this, StudentGroupsActivity.class);
        i.putExtra("studentId", 0);
        startActivity(i);
    }
}
