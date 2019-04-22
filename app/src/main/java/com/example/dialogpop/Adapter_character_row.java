package com.example.dialogpop;

import android.content.Context;
import android.database.ContentObservable;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adapter_character_row implements ListAdapter {

    List<Character> list = new ArrayList<>();
    Context context;

    Adapter_character_row(List<Character> list, Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Character character = list.get(position);
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        convertView = layoutInflater.inflate(R.layout.util_character_dialog_row, null);

        Switch mswitch = convertView.findViewById(R.id.character_switch);

        final View finalConvertView = convertView;

        mswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                ConstraintLayout constraintLayout = finalConvertView.findViewById(R.id.character_row);
                if (character.isStatus()) {
                    constraintLayout.setBackgroundColor(0x00000000);
                }
                else
                    constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.character_row_bg));

                character.setStatus(isChecked);

            }
        });

        TextView name = convertView.findViewById(R.id.character_name);
        name.setText(character.getName());

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
