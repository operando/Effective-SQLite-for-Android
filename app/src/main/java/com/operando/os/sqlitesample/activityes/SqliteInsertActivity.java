package com.operando.os.sqlitesample.activityes;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.operando.os.sqlitesample.R;
import com.operando.os.sqlitesample.databases.SQLiteSampleHelper;
import com.operando.os.sqlitesample.databases.User;
import com.operando.os.sqlitesample.databinding.ActivityMyBinding;
import com.operando.os.sqlitesample.model.Mode;

import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;


public class SqliteInsertActivity extends Activity {

    private static final String TAG = "Shibuya Java";
    private static final int DATASIZE = 50000;

    private boolean inMemory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMyBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_my);
        ButterKnife.inject(this);

//        NO！
//        String path = "/data/data/" + this.getPackageName() + "/file.db";
//        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(path, null);

//        NO！
//        SQLiteDatabase db = openOrCreateDatabase("app.db", Context.MODE_PRIVATE, null);

        binding.compilestatementUnique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteSampleHelper ssh = new SQLiteSampleHelper(SqliteInsertActivity.this, inMemory);
                SQLiteDatabase sb = ssh.getWritableDatabase();

                long start = System.currentTimeMillis();

                SQLiteStatement stat = sb.compileStatement("INSERT INTO " + User.TABLE_NAME_UNIQUE + "(" + User.UserColumns.ADDRESS + ") VALUES(?)");
                try {
                    sb.beginTransaction();
                    for (int i = 0; i < DATASIZE; i++) {
                        stat.bindString(1, "test" + i + "test.com");
                        stat.executeInsert();
                    }
                    sb.setTransactionSuccessful();
                    Log.d(TAG, (System.currentTimeMillis() - start) + "ms");
                    Log.d(TAG, "onCompileStatement===================================================");
                    Log.d(TAG, "onCompileStatement===================================================");
                } finally {
                    sb.endTransaction();
                    sb.close();
                    ssh.close();
                }
            }
        });
    }

    public static Mode createMode(Context context) {
        return new Mode(new Intent(context, SqliteInsertActivity.class), context.getString(R.string.mode_activity_sqlite_insert));
    }


    @OnCheckedChanged(R.id.in_memory)
    void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        inMemory = isChecked;
    }

    public void onExecSQL(View v) {
        SQLiteSampleHelper ssh = new SQLiteSampleHelper(this, inMemory);
        SQLiteDatabase sb = ssh.getWritableDatabase();

        long start = System.currentTimeMillis();
        try {
            sb.beginTransaction();
            for (int i = 0; i < DATASIZE; i++) {
                sb.execSQL("INSERT INTO " + User.TABLE_NAME + " (" + User.UserColumns.ADDRESS + ") VALUES(\"test" + i + "test.com\");");
            }
            Log.d(TAG, (System.currentTimeMillis() - start) + "ms");
            Log.d(TAG, "onExecSQL===================================================");
            Log.d(TAG, "onExecSQL===================================================");
        } finally {
            sb.close();
            ssh.close();
        }
    }

    public void onInsert(View v) {
        SQLiteSampleHelper ssh = new SQLiteSampleHelper(this, inMemory);
        SQLiteDatabase sb = ssh.getWritableDatabase();

        long start = System.currentTimeMillis();
        ContentValues cv = new ContentValues();
        try {
            for (int i = 0; i < DATASIZE; i++) {
                cv.put(User.UserColumns.ADDRESS, "test" + i + "test.com");
                sb.insertOrThrow(User.TABLE_NAME, null, cv);
            }
            Log.d(TAG, (System.currentTimeMillis() - start) + "ms");
            Log.d(TAG, "onInsert===================================================");
            Log.d(TAG, "onInsert===================================================");
        } finally {
            sb.close();
            ssh.close();
        }
    }

    public void onTransaction(View v) {
        SQLiteSampleHelper ssh = new SQLiteSampleHelper(this, inMemory);
        SQLiteDatabase sb = ssh.getWritableDatabase();

        long start = System.currentTimeMillis();
        ContentValues cv = new ContentValues();

        try {
            sb.beginTransaction();
            for (int i = 0; i < DATASIZE; i++) {
                cv.put(User.UserColumns.ADDRESS, "test" + i + "test.com");
                sb.insertOrThrow(User.TABLE_NAME, null, cv);
            }
            sb.setTransactionSuccessful();
            Log.d(TAG, (System.currentTimeMillis() - start) + "ms");
            Log.d(TAG, "onTransaction===================================================");
            Log.d(TAG, "onTransaction===================================================");
        } finally {
            sb.endTransaction();
            sb.close();
            ssh.close();
        }
    }

    public void onCompileStatement(View v) {
        SQLiteSampleHelper ssh = new SQLiteSampleHelper(this, inMemory);
        SQLiteDatabase sb = ssh.getWritableDatabase();

        long start = System.currentTimeMillis();

        SQLiteStatement stat = sb.compileStatement("INSERT INTO " + User.TABLE_NAME + "(" + User.UserColumns.ADDRESS + ") VALUES(?)");
        try {
            sb.beginTransaction();
            for (int i = 0; i < DATASIZE; i++) {
                stat.bindString(1, "test" + i + "test.com");
                stat.executeInsert();
            }
            sb.setTransactionSuccessful();
            Log.d(TAG, (System.currentTimeMillis() - start) + "ms");
            Log.d(TAG, "onCompileStatement===================================================");
            Log.d(TAG, "onCompileStatement===================================================");
        } finally {
            sb.endTransaction();
            sb.close();
            ssh.close();
        }
    }
}
