package com.truck.airlines.airlines.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.truck.airlines.airlines.R;
import com.truck.airlines.airlines.pojos.WeightType;

import java.util.ArrayList;

/**
 * Created by aditya.singh on 6/14/2016.
 */
public class AdapterWeight extends BaseAdapter {


    private final LayoutInflater mInflater;
    private Activity activity;
    private ArrayList<WeightType> weightTypes;

    public AdapterWeight(Activity activity, ArrayList<WeightType> WeightTypes) {
        this.activity = activity;
        this.weightTypes = WeightTypes;
        mInflater = LayoutInflater.from(activity);

    }

    @Override
    public int getCount() {
        return weightTypes.size();
    }

    @Override
    public WeightType getItem(int position) {
        return weightTypes.get(position);
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
        tvMenuName.setText(getItem(position).getWeight());


        return convertView;
    }

}
