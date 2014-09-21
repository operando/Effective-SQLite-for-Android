package com.operando.os.sqlitesample.activityes;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.operando.os.sqlitesample.R;
import com.operando.os.sqlitesample.databases.SQLiteSampleHelper;
import com.operando.os.sqlitesample.databases.User;


public class SqliteSelectActivity extends Activity {

    private static final String TAG = "Shibuya Java";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_select);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                testSelect();
                return null;
            }
        }.execute();
    }

    private void testSelect() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                SQLiteSampleHelper ssh = new SQLiteSampleHelper(SqliteSelectActivity.this);
                SQLiteDatabase sb = ssh.getWritableDatabase();
                try {
                    long start = System.currentTimeMillis();
                    Cursor c = sb.query(User.TABLE_NAME, new String[]{User.UserColumns.ADDRESS,}, null, null, null, null, null);
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


    public void onClick(View v) {
        testSelect();
    }
}
