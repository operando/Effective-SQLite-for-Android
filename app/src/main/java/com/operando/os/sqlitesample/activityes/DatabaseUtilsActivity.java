package com.operando.os.sqlitesample.activityes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.operando.os.sqlitesample.R;
import com.operando.os.sqlitesample.databases.SQLiteSampleHelper;
import com.operando.os.sqlitesample.databases.User;
import com.operando.os.sqlitesample.model.Mode;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DatabaseUtilsActivity extends Activity {

    private static final String TAG = DatabaseUtilsActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_utils);
        ButterKnife.inject(this);
    }

    public static Mode createMode(Context context) {
        return new Mode(new Intent(context, DatabaseUtilsActivity.class), context.getString(R.string.mode_activity_database_utils));
    }

    @OnClick(R.id.databases_utils_dump_cursor)
    void onDumpCursor(View v) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                SQLiteSampleHelper ssh = new SQLiteSampleHelper(DatabaseUtilsActivity.this);
                SQLiteDatabase sb = ssh.getWritableDatabase();
                try {
                    long start = System.currentTimeMillis();
                    Cursor c = sb.query(User.TABLE_NAME, new String[]{User.UserColumns._ID, User.UserColumns.ADDRESS,}, null, null, null, null, null, "10");
                    DatabaseUtils.dumpCursor(c);
                    c.close();
                } finally {
                    sb.close();
                    ssh.close();
                }
                return null;
            }
        }.execute();
    }

    @OnClick(R.id.databases_utils_delete_database)
    void onDeleteDatabase(View v) {
        deleteDatabase(new SQLiteSampleHelper(this).getDatabaseName());
    }
}
