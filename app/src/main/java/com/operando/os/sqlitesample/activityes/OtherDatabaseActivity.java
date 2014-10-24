package com.operando.os.sqlitesample.activityes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.operando.os.sqlitesample.R;
import com.operando.os.sqlitesample.databases.SQLiteSampleHelper;
import com.operando.os.sqlitesample.model.Mode;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtherDatabaseActivity extends Activity {

    private static final String TAG = DatabaseUtilsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_database);
        ButterKnife.inject(this);
    }

    public static Mode createMode(Context context) {
        return new Mode(new Intent(context, OtherDatabaseActivity.class), context.getString(R.string.mode_activity_other_database));
    }

    @OnClick(R.id.other_database_sqlite_version)
    public void onSQLiteVersion(View v) {
        SQLiteSampleHelper ssh = new SQLiteSampleHelper(OtherDatabaseActivity.this);
        SQLiteDatabase sb = ssh.getWritableDatabase();
        SQLiteStatement prog = sb.compileStatement("select sqlite_version() AS sqlite_version");
        String version = prog.simpleQueryForString();
        Log.d(TAG, "version : " + version);
        sb.close();
        ssh.close();
    }
}
