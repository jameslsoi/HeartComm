package com.example.android.heartcomm206;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "heartcomm.db";
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PWD = "pwd";
    private static final int COLUMN_HEARTRATE = 1;
    private static final String COLUMN_EMAIL = "email";
    private static final int COLUMN_PHONENUMBER = 1;
    private static final String COLUMN_CONDITIONS = "med cond";
    private static final String COLUMN_MEDICATIONS = "medications";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);


    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        String insert = "INSERT INTO" + "(" + COLUMN_USERNAME + COLUMN_PWD + COLUMN_HEARTRATE +
                COLUMN_EMAIL + COLUMN_CONDITIONS + COLUMN_MEDICATIONS + ")" +
                "VALUES ('JOANNA', 'abc', 197, 'joanna_01@hotmail.com', 'none', 'none', 0743333666" + ")";
        db.execSQL(insert);

        String insertA = "INSERT INTO" + "(" + COLUMN_USERNAME + COLUMN_PWD + COLUMN_HEARTRATE +
                COLUMN_EMAIL + COLUMN_CONDITIONS + COLUMN_MEDICATIONS + ")" +
                "VALUES ('JOHN', 'DEF', 145, 'john_01@hotmail.com', 'none', 'none', 0743444666" + ")";
        db.execSQL(insertA);

        String insertB = "INSERT INTO" + "(" + COLUMN_USERNAME + COLUMN_PWD + COLUMN_HEARTRATE +
                COLUMN_EMAIL + COLUMN_CONDITIONS + COLUMN_MEDICATIONS + ")" +
                "VALUES ('ADAM', 'QWERTY', 185, 'adam_01@hotmail.com', 'none', 'none', 0743888666" + ")";
        db.execSQL(insertB);
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE" + TABLE_USERS + "(" +
                COLUMN_USERNAME + " VARCHAR2 (15) PRIMARY KEY NOT NULL" +
                COLUMN_PWD + "VARCHAR2 (10) " +
                COLUMN_HEARTRATE + " INTEGER" +
                COLUMN_EMAIL + "VARCHAR2 (40) " +
                COLUMN_CONDITIONS + "VARCHAR2 (50) " +
                COLUMN_MEDICATIONS + "VARCHAR2 (50) " +
                COLUMN_PHONENUMBER + "INTEGER" +
                ");";
        db.execSQL(query);
    }
    private SQLiteDatabase db;

    public String[] getUserInfo() {
        Cursor cursor = this.db.query(TABLE_USERS, new String[]{COLUMN_USERNAME}, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            String[] str = new String[cursor.getCount()];
            int i = 0;

            while (cursor.moveToNext()) {
                str[i] = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
                i++;
            }
            return str;
        } else {
            return new String[]{};
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    }
}

