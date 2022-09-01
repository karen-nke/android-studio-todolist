package com.bignerdranch.android.criminalintent.database;

public class ToDoDbSchema {
    public static final class ToDoTable{
        public static final String NAME= "todos";

        public static final class Cols{
            public static final String UUID ="uuid";
            public static final String TITLE ="title";
            public static final String DATE ="date";
            public static final String SOLVED ="solved";
            public static final String COLLAB = "collab";
            public static final String DETAILS ="details";
            public static final String CAT ="cat";
        }
    }
}
