package com.example.wrup.lab04.model.dbTables;


public class StudentGroupTable {
    public static final String TABLE_NAME = "Student_Group";

    @Override
    public String toString() {
        return TABLE_NAME;
    }

    public class StudentGroupColumns {
        public static final String STUDENT_ID = "id_student";
        public static final String GROUP_ID = "id_group";
    }
}
