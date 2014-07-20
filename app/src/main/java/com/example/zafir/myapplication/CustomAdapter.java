package com.example.zafir.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;

/**
 * Created by Yazan on 2014-07-19.
 *
 * This customer adapter is needed to display rows of different colors.
 * We want to alternate between three colors, all of which are in colors.xml in the res folder
 * This customer adapter extends ArrayAdapter
 */
public class CustomAdapter extends SimpleCursorAdapter {

    private int[] colors = new int[] { Color.parseColor("#F0F0F0"), Color.parseColor("#D2E4FC") };
    //private int[] colors = new int[] { R., 0x30808080 };
    public CustomAdapter(Context context, int layout, Cursor c,
                         String[] from, int[] to) {
        super(context, layout, c, from, to);
    }
    /**
     * Display rows in alternating colors
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        int colorPos = position % colors.length;
        view.setBackgroundColor(colors[colorPos]);
        return view;
    }
}
