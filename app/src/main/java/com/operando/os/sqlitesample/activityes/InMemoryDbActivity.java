package com.operando.os.sqlitesample.activityes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.operando.os.sqlitesample.R;
import com.operando.os.sqlitesample.model.Mode;

public class InMemoryDbActivity extends Activity {

    private static final int DATASIZE = 50000;

    public static Mode createMode(Context context) {
        return new Mode(new Intent(context, InMemoryDbActivity.class), context.getString(R.string.mode_activity_in_memory_database));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_memory_db);
    }
}
