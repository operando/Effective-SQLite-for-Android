package com.operando.os.sqlitesample.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class InMemoryDbHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String USER_TABLE_CREATE =
            "CREATE TABLE " + User.TABLE_NAME + " (" +
                    User.UserColumns._ID + "  INTEGER PRIMARY KEY AUTOINCREMENT," +
                    User.UserColumns.ADDRESS + " TEXT);"; //UNIQUE);";


    public InMemoryDbHelper(Context context) {
        super(context, null, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}