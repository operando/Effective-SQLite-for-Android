package com.operando.os.sqlitesample.activityes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.operando.os.sqlitesample.R;
import com.operando.os.sqlitesample.adapters.SelectModeListAdapter;
import com.operando.os.sqlitesample.model.Mode;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SelectModeActivity extends Activity {

    @InjectView(R.id.mode_list)
    ListView mModeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mode);
        ButterKnife.inject(this);

        List<Mode> list = new ArrayList<Mode>();
        list.add(SqliteInsertActivity.createMode(this));
        list.add(SqliteSelectActivity.createMode(this));
        list.add(DatabaseUtilsActivity.createMode(this));
        list.add(OtherDatabaseActivity.createMode(this));
        mModeList.setAdapter(new SelectModeListAdapter(this, list));
        mModeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                startActivity(((Mode) mModeList.getAdapter().getItem(position)).getIntent());
            }
        });
    }
}
