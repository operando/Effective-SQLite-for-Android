package com.operando.os.sqlitesample.activityes;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.operando.os.sqlitesample.R;
import com.operando.os.sqlitesample.databases.SQLiteSampleHelper;
import com.operando.os.sqlitesample.databases.SelectTestTable;


public class SqliteSelectActivity extends Activity {

    private static final String TAG = "Shibuya Java";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_select);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                SQLiteSampleHelper ssh = new SQLiteSampleHelper(SqliteSelectActivity.this);
                SQLiteDatabase sb = ssh.getWritableDatabase();
                long start = System.currentTimeMillis();
                try {
                    SQLiteStatement stat = sb.compileStatement("INSERT INTO " + SelectTestTable.SELECTTEST_TABLE_NAME + " values(?)");
                    sb.beginTransaction();
                    for (int i = 0; i < 1000000; i++) {
                        stat.bindString(1, "test" + i);
                        stat.executeInsert();
                    }
                    sb.setTransactionSuccessful();
                } finally {
                    sb.endTransaction();
                    Cursor c = sb.rawQuery("select * from " + SelectTestTable.SELECTTEST_TABLE_NAME + ";", null);
                    Log.d(TAG, "===================================================");
                    Log.d(TAG, c.getCount() + "");
                    Log.d(TAG, (System.currentTimeMillis() - start) + "ms");
                    Log.d(TAG, "===================================================");

                    sb.close();
                    ssh.close();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(SqliteSelectActivity.this, "Insert End.", Toast.LENGTH_LONG).show();
            }
        }.execute();
    }


    public void onClick(View v) {
        SQLiteSampleHelper ssh = new SQLiteSampleHelper(this);
        SQLiteDatabase sb = ssh.getWritableDatabase();
        try {
            long start = System.currentTimeMillis();
            Cursor c = sb.rawQuery("select * from " + SelectTestTable.SELECTTEST_TABLE_NAME + ";", null);
            Log.d(TAG, (System.currentTimeMillis() - start) + "ms");
            Log.d(TAG, "===================================================");
            Log.d(TAG, c.getCount() + "");
            Log.d(TAG, "===================================================");
            c.close();
        } finally {
            sb.close();
            ssh.close();
        }
    }
}
