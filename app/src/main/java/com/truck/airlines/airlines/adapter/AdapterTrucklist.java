package com.truck.airlines.airlines.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.truck.airlines.airlines.R;
import com.truck.airlines.airlines.pojos.Truck;

import java.util.ArrayList;

/**
 * Created by aditya.singh on 6/14/2016.
 */
public class AdapterTrucklist extends BaseAdapter {


    private final LayoutInflater mInflater;
    private Activity activity;
    private ArrayList<Truck> sideMenuItems;

    public AdapterTrucklist(Activity activity, ArrayList<Truck> sideMenuItems) {
        this.activity = activity;
        this.sideMenuItems = sideMenuItems;
        mInflater = LayoutInflater.from(activity);

    }

    @Override
    public int getCount() {
        return sideMenuItems.size();
    }

    @Override
    public Truck getItem(int position) {
        return sideMenuItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = mInflater.inflate(
                    R.layout.truck_item, parent, false);
        }


        TextView tvSource = (TextView) convertView.findViewById(R.id.tvSource);
        tvSource.setText(getItem(position).getSourceCity().split(",")[0]);
        TextView tvDestination = (TextView) convertView.findViewById(R.id.tvDestination);
        tvDestination.setText(getItem(position).getDestinationCity().split(",")[0]);

        TextView tvWeight = (TextView) convertView.findViewById(R.id.tvWeight);
        tvWeight.setText(getItem(position).getWeightId()+"");

        TextView tvTruckType = (TextView) convertView.findViewById(R.id.tvTruckType);
        tvTruckType.setText(getItem(position).getTruckTypeId()+"");

//        TextView tvMaterialType = (TextView) convertView.findViewById(R.id.tvMaterialType);
//        tvMaterialType.setText(getItem(position).getTruckId()+"");

        Button btnGetFrightPrice = (Button) convertView.findViewById(R.id.btnGetFrightPrice);
        btnGetFrightPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(activity,"Under Development",Toast.LENGTH_SHORT).show();
            }
        });

        Button btnCallNow = (Button) convertView.findViewById(R.id.btnCallNow);
        btnCallNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,"Under Development",Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }


}
