package com.truck.airlines.airlines.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.truck.airlines.airlines.R;
import com.truck.airlines.airlines.pojos.State;

import java.util.ArrayList;

/**
 * Created by aditya.singh on 8/9/2018.
 */

public class AdapterState extends BaseAdapter {

    private final LayoutInflater mInflater;
    Activity activity;
    ArrayList<State> citiesSets;


    public AdapterState(Activity activity, ArrayList<State> citiesSets) {
        this.activity = activity;
        this.citiesSets = citiesSets;
        mInflater = LayoutInflater.from(activity);

    }

    @Override
    public int getCount() {
        return citiesSets.size();
    }

    @Override
    public State getItem(int pos) {
        return citiesSets.get(pos);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.menu_name, parent, false);
        }
        TextView tvMenuName = (TextView) convertView.findViewById(R.id.tvMenuName);

        tvMenuName.setText(citiesSets.get(position).getFullname());
        if (position == 0)
            tvMenuName.setTextColor(activity.getResources().getColor(R.color.grey_text));
        else
            tvMenuName.setTextColor(activity.getResources().getColor(R.color.black_text));
        return convertView;
    }








}
