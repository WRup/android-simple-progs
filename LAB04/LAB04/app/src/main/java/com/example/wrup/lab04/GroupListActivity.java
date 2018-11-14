package com.example.wrup.lab04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.wrup.lab04.controler.DbManager;
import com.example.wrup.lab04.model.objects.Group;

import java.util.ArrayList;

public class GroupListActivity extends AppCompatActivity {

    private static final String Laboratory = "Laboratory";
    private static final String Practice = "Practice";
    private static final String Lecture = "Lecture";

    private DbManager dbManager;
    private ArrayList<Group> groupListLabType = new ArrayList<>();
    private ArrayList<Group> groupListPracType = new ArrayList<>();
    private ArrayList<Group> groupListLecType = new ArrayList<>();
    private ArrayAdapter<String> labAdapter;
    private ArrayAdapter<String> praAdapter;
    private ArrayAdapter<String> lecAdapter;
    private ListView groupList;
    private ListView groupList2;
    private ListView groupList3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);
        Bundle extras = getIntent().getExtras();
        ArrayList<Integer> array = extras.getIntegerArrayList("groupids");

        dbManager = new DbManager(this);

        groupListLabType = dbManager.getGroupsByType(Laboratory);
        groupListPracType = dbManager.getGroupsByType(Practice);
        groupListLecType = dbManager.getGroupsByType(Lecture);

        groupList = (ListView) findViewById(R.id.groupsList);
        groupList2 = (ListView) findViewById(R.id.groupList2);
        groupList3 = (ListView) findViewById(R.id.groupList3);

        groupList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        groupList2.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        groupList3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        labAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice, getGroupNames(groupListLabType));
        groupList.setAdapter(labAdapter);
        groupList.requestFocus();
        praAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice, getGroupNames(groupListPracType));
        groupList2.setAdapter(praAdapter);
        groupList2.requestFocus();
        lecAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice, getGroupNames(groupListLecType));
        groupList3.setAdapter(lecAdapter);
        groupList3.requestFocus();

        if (array != null) {
            for (Integer idGroup : array) {
                switch (dbManager.getGroup(idGroup).getType()) {
                    case "Laboratory": {
                        int index = groupListLabType.indexOf(getGroupWithId(idGroup,groupListLabType));
                        groupList.setItemChecked(index, true);
                        break;
                    }
                    case "Practice": {
                        int index = groupListPracType.indexOf(getGroupWithId(idGroup,groupListPracType));
                        groupList2.setItemChecked(index, true);
                        break;
                    }
                    case "Lecture": {
                        int index = groupListLecType.indexOf(getGroupWithId(idGroup,groupListLecType));
                        groupList3.setItemChecked(index, true);
                        break;
                    }
                }
            }
        }

    }

    public void acceptClicked(View view) {
        ArrayList<Integer> groupIds = new ArrayList<>();

        if(groupList.getCheckedItemPosition()>=0) {
            Group labChecked = groupListLabType.get(groupList.getCheckedItemPosition());
            groupIds.add(labChecked.getId());
        }
        if(groupList2.getCheckedItemPosition() >= 0) {
            Group praChecked = groupListPracType.get(groupList2.getCheckedItemPosition());
            groupIds.add(praChecked.getId());
        }
        if(groupList3.getCheckedItemPosition() >= 0){
            Group lecChecked = groupListLecType.get(groupList3.getCheckedItemPosition());
            groupIds.add(lecChecked.getId());
        }

        Intent intent = new Intent();
        intent.putExtra("newIds", groupIds);
        setResult(RESULT_OK, intent);
        finish();
    }

    private Group getGroupWithId(int id, ArrayList<Group> arrayList) {
        for (Group group : arrayList) {
            if (group.getId() == id) {
                return group;
            }
        }
        return null;
    }

    private ArrayList<String> getGroupNames(ArrayList<Group> groupList){
        ArrayList<String> groupNames = new ArrayList<>();
        if(groupList!= null) {
            for (Group gr : groupList)
                groupNames.add(gr.getName());
            return groupNames;
        }
        else
            return null;
    }
}
