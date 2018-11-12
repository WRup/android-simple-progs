package com.example.wrup.lab04.model.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.wrup.lab04.model.dbTables.GroupTable;
import com.example.wrup.lab04.model.iDao.IDaoWrite;
import com.example.wrup.lab04.model.objects.Group;


public class GroupDaoWrite implements IDaoWrite<Group>{

    private SQLiteDatabase db;

    public GroupDaoWrite(SQLiteDatabase db){ this.db = db; }

    @Override
    public void insert(Group group) {
        ContentValues values = new ContentValues();

        values.put(GroupTable.GroupColumns.NAME, group.getName());
        values.put(GroupTable.GroupColumns.TYPE, group.getType());
        int id = (int) db.insert(GroupTable.TABLE_NAME, null, values);
        group.setId(id);
    }

    @Override
    public void update(Group model) {
    }

    @Override
    public void delete(int id) {

    }
}
