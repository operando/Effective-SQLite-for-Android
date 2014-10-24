package com.operando.os.sqlitesample.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.operando.os.sqlitesample.R;
import com.operando.os.sqlitesample.model.Mode;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SelectModeListAdapter extends BindableAdapter<Mode> {

    static class ViewHolder {
        @InjectView(R.id.select_mode_title)
        TextView mSelectModeTitle;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    public SelectModeListAdapter(Context context, List<Mode> episodeList) {
        super(context, episodeList);
    }

    @Override
    public View newView(LayoutInflater inflater, int position, ViewGroup container) {
        View v = inflater.inflate(R.layout.list_item_select_mode, container, false);
        ViewHolder vh = new ViewHolder(v);
        v.setTag(vh);
        return v;
    }

    @Override
    public void bindView(Mode item, int position, View view) {
        ViewHolder vh = (ViewHolder) view.getTag();
        vh.mSelectModeTitle.setText(item.getTitle());
    }
}
