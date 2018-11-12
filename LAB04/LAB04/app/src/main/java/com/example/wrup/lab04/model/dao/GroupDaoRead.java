package com.example.wrup.lab04.model.dao;

import com.example.wrup.lab04.model.iDao.IDaoRead;
import com.example.wrup.lab04.model.objects.Group;
import com.example.wrup.lab04.model.dbTables.GroupTable;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class GroupDaoRead implements IDaoRead {

    private SQLiteDatabase db;

    public GroupDaoRead(SQLiteDatabase db){
        this.db = db;
    }

    public Group findById(int id){
        String query = "SELECT * FROM " + GroupTable.TABLE_NAME + " WHERE " +
                        GroupTable.GroupColumns.ID + "=" + id + ";";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        if(c.getCount() >= 1){
            throw new RuntimeException("TOO MANY ROWS EXCEPTION");
        }
        else if(c.getCount() <= 0){
            throw new RuntimeException("NO DATA FOUND EXCEPTION");
        }
        String name = c.getString(c.getColumnIndex(GroupTable.GroupColumns.NAME));
        String type = c.getString(c.getColumnIndex(GroupTable.GroupColumns.TYPE));
        c.close();
        return new Group(id, name, type);
    }


    public ArrayList<Group> findAll(){
        ArrayList<Group> groups = new ArrayList<>();

        String query = "SELECT * FROM " + GroupTable.TABLE_NAME + ";";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        if(c.getCount() <= 0){
            groups.clear();
            c.close();
            return groups;
        }
        do {
            int id = c.getInt(c.getColumnIndex(GroupTable.GroupColumns.ID));
            String name = c.getString(c.getColumnIndex(GroupTable.GroupColumns.NAME));
            String type = c.getString(c.getColumnIndex(GroupTable.GroupColumns.TYPE));

            groups.add(new Group(id, name,type));

        } while (c.moveToNext());

        return groups;
    }

    @Override
    public ArrayList findAllByType(String type) {
        ArrayList<Group> groups = new ArrayList<>();

        String query = "SELECT * FROM " + GroupTable.TABLE_NAME + " WHERE " + GroupTable.GroupColumns.TYPE + " = " + type +";";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        if(c.getCount() <= 0){
            groups.clear();
            c.close();
            return groups;
        }
        do {
            int id = c.getInt(c.getColumnIndex(GroupTable.GroupColumns.ID));
            String name = c.getString(c.getColumnIndex(GroupTable.GroupColumns.NAME));

            groups.add(new Group(id, name,type));

        } while (c.moveToNext());

        return groups;

    }
}