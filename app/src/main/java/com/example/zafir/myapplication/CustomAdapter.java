package com.example.zafir.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ArrayAdapter;

/**
 * Created by Yazan on 2014-07-19.
 *
 * This customer adapter is needed to display rows of different colors.
 * We want to alternate between three colors, all of which are in colors.xml in the res folder
 * This customer adapter extends ArrayAdapter
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
            view.setBackgroundColor(Color.BLUE);

        }
        else {
            view.setBackgroundColor(Color.BLACK);
        }

        return view;
    }
}
