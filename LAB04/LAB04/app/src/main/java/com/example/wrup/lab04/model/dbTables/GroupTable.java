package com.example.wrup.lab04.model.dbTables;


public class GroupTable {
    public static final String TABLE_NAME = "AcademicGroup";

    @Override
    public String toString() {
        return TABLE_NAME;
    }

    public class GroupColumns {
        public static final String ID = "id_group";
        public static final String NAME = "name";
        public static final String TYPE = "type";
    }
}