package com.truck.airlines.airlines.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.truck.airlines.airlines.R;
import com.truck.airlines.airlines.pojos.LoadSummaryData;
import com.truck.airlines.airlines.pojos.TruckInfo;
import com.truck.airlines.airlines.utils.Util;

import java.util.List;

public class AdapterTruckListing extends RecyclerView.Adapter<AdapterTruckListing.ViewHolder> {

    Context c;
    List<TruckInfo> data;

    public AdapterTruckListing(Context c, List<TruckInfo> data) {
        this.c = c;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.truck_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //BIND DATA
        holder.tvDate.setText(Util.getDateFromUnixTime(data.get(position).getCreatedAt().split(" ")[0]));
        holder.tvPrice.setText(data.get(position).getCategoryTypeName());
        holder.tvCatName.setText(data.get(position).getRegistrationNumber());
        holder.tvSource.setText(data.get(position).getUsername());
        holder.tvDestination.setText(data.get(position).getMobile());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate,tvPrice,tvCatName,tvMaterialType,tvSource,tvDestination;

        public ViewHolder(View itemView) {
            super(itemView);

            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            tvCatName = (TextView) itemView.findViewById(R.id.tvCatName);
            tvMaterialType = (TextView) itemView.findViewById(R.id.tvMaterialType);
            tvSource = (TextView) itemView.findViewById(R.id.tvSource);
            tvDestination = (TextView) itemView.findViewById(R.id.tvDestination);

        }

    }
}
