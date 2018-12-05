package com.truck.airlines.airlines.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.truck.airlines.airlines.R;
import com.truck.airlines.airlines.pojos.Material;
import com.truck.airlines.airlines.pojos.Vehicletype;

import java.util.List;

/**
 * Created by aditya.singh on 6/14/2016.
 */
public class AdapterMaterialName extends BaseAdapter {


    private final LayoutInflater mInflater;
    private Activity activity;
    private List<Material> materialTypes;

    public AdapterMaterialName(Activity activity, List<Material> MaterialTypes) {
        this.activity = activity;
        this.materialTypes = MaterialTypes;
        mInflater = LayoutInflater.from(activity);

    }

    @Override
    public int getCount() {
        return materialTypes.size();
    }

    @Override
    public Material getItem(int position) {
        return materialTypes.get(position);
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
        tvMenuName.setText(getItem(position).getName());


        return convertView;
    }

}
