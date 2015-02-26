package com.codepath.pixurch.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.codepath.pixurch.R;
import com.codepath.pixurch.models.ImageResult;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ResultAdapter extends ArrayAdapter {
    public ResultAdapter(Context context, ArrayList<ImageResult> images) {
        super(context, android.R.layout.simple_list_item_1, images);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

        }
        return convertView;
    }
}
