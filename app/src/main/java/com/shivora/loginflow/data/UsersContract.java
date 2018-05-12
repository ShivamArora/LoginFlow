package com.shivora.loginflow.data;

import android.provider.BaseColumns;

public class UsersContract {
    private UsersContract(){

    }

    public static final class UsersEntry implements BaseColumns{
        public static final String TABLE_NAME = "Users";
        public static final String COLUMN_NAME = "NAME";
        public static final String COLUMN_EMAIL = "EMAIL";
        public static final String COLUMN_PHONE = "PHONE";
        public static final String COLUMN_PASSWORD = "PASSWORD";
        public static final String COLUMN_SECRET_KEY = "SECRET_KEY";
    }
}
