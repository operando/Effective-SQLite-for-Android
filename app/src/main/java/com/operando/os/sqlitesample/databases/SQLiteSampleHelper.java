package com.operando.os.sqlitesample.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteSampleHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "sample.db";
    private static final int DB_VERSION = 1;
    private static final String SAMPLE_TABLE_CREATE =
            "CREATE TABLE " + SampleTable.SAMPLE_TABLE_NAME + " (" +
                    SampleTable.SAMPLE_TEST_COLUMN + " TEXT);";
    private static final String SELECTTEST_TABLE_CREATE =
            "CREATE TABLE " + SelectTestTable.SELECTTEST_TABLE_NAME + " (" +
                    SelectTestTable.SELECTTEST_TEST_COLUMN + " TEXT);";


    public SQLiteSampleHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SAMPLE_TABLE_CREATE);
        db.execSQL(SELECTTEST_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}