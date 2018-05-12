package com.shivora.loginflow.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.shivora.loginflow.ForgotPasswordActivity;
import com.shivora.loginflow.data.UsersContract;
import com.shivora.loginflow.data.UsersDbHelper;

public class UsersUtil {
    public static boolean registerUser(Context context, String name, String email, String phone, String password, String secretKey){
        UsersDbHelper dbHelper = new UsersDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(UsersContract.UsersEntry.COLUMN_NAME,name);
        contentValues.put(UsersContract.UsersEntry.COLUMN_EMAIL,email);
        contentValues.put(UsersContract.UsersEntry.COLUMN_PHONE,phone);
        contentValues.put(UsersContract.UsersEntry.COLUMN_PASSWORD,password);
        contentValues.put(UsersContract.UsersEntry.COLUMN_SECRET_KEY,secretKey);

        long result = db.insert(UsersContract.UsersEntry.TABLE_NAME,null,contentValues);
        if (result>0)
            return true;
        return false;
    }

    public static int loginUser(Context context,String email, String password){
        UsersDbHelper dbHelper = new UsersDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columns = new String[]{UsersContract.UsersEntry._ID,UsersContract.UsersEntry.COLUMN_EMAIL, UsersContract.UsersEntry.COLUMN_PASSWORD};
        String selection = UsersContract.UsersEntry.COLUMN_EMAIL +"  = ? AND "+ UsersContract.UsersEntry.COLUMN_PASSWORD+" = ?";
        String[] selectionArgs = new String[]{email,password};

        Cursor cursor = db.query(UsersContract.UsersEntry.TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        if (cursor.moveToNext()) {
            System.out.println(cursor.getColumnCount());
            System.out.println(cursor.getCount());

            if (cursor.getString(1).equals(email) && cursor.getString(2).equals(password)) {
                return cursor.getInt(0);
            }
        }
        return 0;
    }

    public static User getUserDetails(Context context,int userId){
        User user = null;
        UsersDbHelper dbHelper = new UsersDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columns = new String[]{UsersContract.UsersEntry.COLUMN_NAME,UsersContract.UsersEntry.COLUMN_EMAIL, UsersContract.UsersEntry.COLUMN_PHONE};
        String selection = UsersContract.UsersEntry._ID +"  = ? ";
        String[] selectionArgs = new String[]{String.valueOf(userId)};

        Cursor cursor = db.query(UsersContract.UsersEntry.TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        if (cursor.moveToNext()) {
            System.out.println(cursor.getColumnCount());
            System.out.println(cursor.getCount());

            String name = cursor.getString(0);
            String email = cursor.getString(1);
            String phone = cursor.getString(2);
            user = new User(name, email, phone);
        }
        return user;
    }

    public static boolean verifySecretKey(Context context,String email, String secretKey) {
        UsersDbHelper dbHelper = new UsersDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columns = new String[]{UsersContract.UsersEntry.COLUMN_SECRET_KEY};
        String selection = UsersContract.UsersEntry.COLUMN_SECRET_KEY +"  = ? AND "+ UsersContract.UsersEntry.COLUMN_EMAIL+" = ? ";
        String[] selectionArgs = new String[]{secretKey,email};

        Cursor cursor  = db.query(UsersContract.UsersEntry.TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        if (cursor.moveToNext())
            return true;
        return false;
    }

    public static String getPassword(Context context, String email) {
        UsersDbHelper dbHelper = new UsersDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columns = new String[]{UsersContract.UsersEntry.COLUMN_PASSWORD};
        String selection = UsersContract.UsersEntry.COLUMN_EMAIL +"  = ? ";
        String[] selectionArgs = new String[]{email};

        Cursor cursor  = db.query(UsersContract.UsersEntry.TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        if (cursor.moveToNext())
            return cursor.getString(0);
        return "";
    }
}
