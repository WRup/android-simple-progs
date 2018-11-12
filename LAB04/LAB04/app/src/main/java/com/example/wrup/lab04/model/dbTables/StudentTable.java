package com.example.wrup.lab04.model.dbTables;


public class StudentTable {
    public static final String TABLE_NAME = "Student";

    @Override
    public String toString() {
        return TABLE_NAME;
    }

    public class StudentColumns {
        public static final String ID = "id_student";
        public static final String NAME = "name";
        public static final String SURNAME = "surname";
    }
}
