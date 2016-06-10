package com.operando.os.sqlitesample.activityes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.operando.os.sqlitesample.R;
import com.operando.os.sqlitesample.databases.SQLiteSampleHelper;
import com.operando.os.sqlitesample.databases.User;
import com.operando.os.sqlitesample.model.Mode;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class SqliteSelectActivity extends Activity {

    private static final String TAG = SqliteSelectActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_select);
        ButterKnife.inject(this);
    }

    public static Mode createMode(Context context) {
        return new Mode(new Intent(context, SqliteSelectActivity.class), context.getString(R.string.mode_activity_sqlite_select));
    }

    @OnClick(R.id.sqlite_select_text)
    public void onClick(View v) {
        testSelect();
    }

    @OnClick(R.id.sqlite_select_record_count)
    public void onRecordCount(View v) {
        SQLiteSampleHelper ssh = new SQLiteSampleHelper(SqliteSelectActivity.this);
        SQLiteDatabase sb = ssh.getReadableDatabase();
        try {
            long start = System.currentTimeMillis();
            Log.d(TAG, "recodeCount : " + DatabaseUtils.queryNumEntries(sb, User.TABLE_NAME));
            Log.d(TAG, "getCount Time : " + (System.currentTimeMillis() - start) + "ms");
            Log.d(TAG, "===================================================");
        } finally {
            sb.close();
            ssh.close();
        }
    }

    @OnClick(R.id.sqlite_select_no_limit)
    public void onNoLimit(View v) {
        String address = "test5000test.com";
        String query = "SELECT * FROM " + User.TABLE_NAME + " WHERE " + User.UserColumns.ADDRESS + " = ?";
        select(query, address);
    }

    @OnClick(R.id.sqlite_select_limit)
    public void onLimit(View v) {
        String address = "test5000test.com";
        String query = "SELECT * FROM " + User.TABLE_NAME + " WHERE " + User.UserColumns.ADDRESS + " = ? limit 1";
        select(query, address);
    }

    @OnClick(R.id.sqlite_select_unique_column_limit)
    public void onUniqueColumnLimit(View v) {
        String address = "test20000test.com";
        String query = "SELECT * FROM " + User.TABLE_NAME_UNIQUE + " WHERE " + User.UserColumns.ADDRESS + " = ?";
        select(query, address);
    }

    private void testSelect() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                SQLiteSampleHelper ssh = new SQLiteSampleHelper(SqliteSelectActivity.this);
                SQLiteDatabase sb = ssh.getReadableDatabase();
                try {
                    long start = System.currentTimeMillis();
                    Cursor c = sb.query(User.TABLE_NAME, new String[]{User.UserColumns._ID, User.UserColumns.ADDRESS,}, null, null, null, null, null);
                    Log.d(TAG, "Select Time : " + (System.currentTimeMillis() - start) + "ms");
                    Log.d(TAG, "===================================================");
                    Log.d(TAG, c.getCount() + "");
                    Log.d(TAG, "getCount Time : " + (System.currentTimeMillis() - start) + "ms");
                    Log.d(TAG, "===================================================");
                    c.close();
                } finally {
                    sb.close();
                    ssh.close();
                }
                return null;
            }
        }.execute();
    }

    public static void dumpLastPosition(Cursor cursor) {
        Log.d("dumpLastPosition", "===========================");
        if (cursor != null) {
            int startPosition = cursor.getPosition();
            cursor.moveToPosition(-1);
            if (cursor.moveToLast()) {
                String[] columnNames = cursor.getColumnNames();
                int length = columnNames.length;
                for (int i = 0; i < length; i++) {
                    String value;
                    try {
                        value = cursor.getString(i);
                    } catch (SQLiteException e) {
                        value = "<unprintable>";
                    }
                    Log.d("dumpLastPosition", columnNames[i] + " : " + value);
                }
            }
            cursor.moveToPosition(startPosition);
        }
        Log.d("dumpLastPosition", "===========================");
    }

    private void select(String query, String address) {
        SQLiteSampleHelper ssh = new SQLiteSampleHelper(SqliteSelectActivity.this);
        SQLiteDatabase sb = ssh.getReadableDatabase();
        try {
            long startQuery = System.currentTimeMillis();
            Cursor c = sb.rawQuery(query, new String[]{address});
            long endQuery = System.currentTimeMillis();
            Log.d(TAG, "Select Time : " + (endQuery - startQuery) + "ms");
            Log.d(TAG, "===================================================");
            long startCount = System.currentTimeMillis();
            c.getCount();
            long endCount = System.currentTimeMillis();
            Log.d(TAG, "getCount Time : " + (endCount - startCount) + "ms");
            Log.d(TAG, "===================================================");
            DatabaseUtils.dumpCursor(c);
            c.close();
        } finally {
            sb.close();
            ssh.close();
        }
    }
}
