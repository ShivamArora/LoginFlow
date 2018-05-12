package com.shivora.loginflow.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsersDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "usersdb.db";
    private static final int DATABASE_VERSION = 1;

    public UsersDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TABLE = "CREATE TABLE "+ UsersContract.UsersEntry.TABLE_NAME + "("
                + UsersContract.UsersEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UsersContract.UsersEntry.COLUMN_NAME + " TEXT NOT NULL,"
                + UsersContract.UsersEntry.COLUMN_EMAIL + " TEXT NOT NULL,"
                + UsersContract.UsersEntry.COLUMN_PHONE + " TEXT NOT NULL,"
                + UsersContract.UsersEntry.COLUMN_PASSWORD + " TEXT NOT NULL,"
                + UsersContract.UsersEntry.COLUMN_SECRET_KEY + " TEXT NOT NULL);";
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ UsersContract.UsersEntry.TABLE_NAME+";");
        onCreate(db);
    }
}
