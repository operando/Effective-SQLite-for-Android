package com.operando.os.sqlitesample.activityes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.operando.os.sqlitesample.R;
import com.operando.os.sqlitesample.model.Mode;

public class DatabaseUtilsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_utils);
    }

    public static Mode createMode(Context context) {
        return new Mode(new Intent(context, DatabaseUtilsActivity.class), context.getString(R.string.mode_activity_database_utils));
    }
}
