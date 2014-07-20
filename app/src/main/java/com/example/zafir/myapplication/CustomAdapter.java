package com.example.zafir.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ArrayAdapter;

/**
 * Created by Yazan on 2014-07-19.
 */
public class CustomAdapter extends ArrayAdapter<String> {

    private final Context context;
    private String[] values;

    public CustomAdapter(Context context, String[] values) {
        super(context, R.layout.activity_my, values);

        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View view = super.getView(position, convertView, parent);


        if (position % 2 == 1) {
            view.setBackgroundColor(R.color.background_blue);

        }
        else {
            view.setBackgroundColor(R.color.background_green);
        }

        return view;
    }
}
