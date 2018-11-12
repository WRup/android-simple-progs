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

    private DbManager dbManager;
    private ArrayList<Group> groupList = new ArrayList<>();
    private ArrayAdapter<Group> adapter;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);
        Bundle extras = getIntent().getExtras();
        String groupids = extras.getString("groupids");
        String[] array = groupids.isEmpty() ? null : groupids.split(",");

        dbManager = new DbManager(this);
        groupList = dbManager.getGroups();
        list = (ListView) findViewById(R.id.groupsList);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_multichoice, groupList);
        list.setAdapter(adapter);

        if (array != null) {
            for (String idString : array) {
                int index = groupList.indexOf(getGroupWithId(Integer.parseInt(idString)));
                list.setItemChecked(index, true);
            }
        }
    }

    public void acceptClicked(View view) {
        ArrayList<Integer> groupIds = new ArrayList<Integer>();

        SparseBooleanArray checked = list.getCheckedItemPositions();
        int size = checked.size();
        for (int i = 0; i < size; i++) {
            int key = checked.keyAt(i);
            boolean value = checked.get(key);
            if (value) {
                groupIds.add(((Group)list.getItemAtPosition(key)).getId());
            }
        }

        Intent intent = new Intent();
        intent.putExtra("newIds", android.text.TextUtils.join(",", groupIds));
        setResult(RESULT_OK, intent);
        finish();
    }

    private Group getGroupWithId(int id) {
        for (Group group : groupList) {
            if (group.getId() == id) {
                return group;
            }
        }
        return null;
    }
}
