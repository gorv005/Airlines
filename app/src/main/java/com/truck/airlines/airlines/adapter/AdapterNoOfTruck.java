package com.truck.airlines.airlines.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.truck.airlines.airlines.R;

import java.util.ArrayList;

/**
 * Created by aditya.singh on 6/14/2016.
 */
public class AdapterNoOfTruck extends BaseAdapter {


    private final LayoutInflater mInflater;
    private Activity activity;
    private ArrayList<String> Strings;

    public AdapterNoOfTruck(Activity activity, ArrayList<String> strings) {
        this.activity = activity;
        this.Strings = strings;
        mInflater = LayoutInflater.from(activity);

    }

    @Override
    public int getCount() {
        return Strings.size();
    }

    @Override
    public String getItem(int position) {
        return Strings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = mInflater.inflate(
                    R.layout.item_material_type, parent, false);
        }


        TextView tvMenuName = (TextView) convertView.findViewById(R.id.tvMenuName);
        tvMenuName.setText(getItem(position));


        return convertView;
    }

}
