package com.truck.airlines.airlines.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.truck.airlines.airlines.R;
import com.truck.airlines.airlines.pojos.LoadSummaryData;

import java.util.ArrayList;
import java.util.List;

public class AdapterLoadsSummary extends RecyclerView.Adapter<AdapterLoadsSummary.ViewHolder> {

    Context c;
    List<LoadSummaryData> data;

    public AdapterLoadsSummary(Context c, List<LoadSummaryData> data) {
        this.c = c;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.item_loads_summary, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //BIND DATA
        holder.tvDate.setText(data.get(position).getDate().split(" ")[0]);
        holder.tvPrice.setText(data.get(position).getPrice());
        holder.tvCatName.setText(data.get(position).getCategoryName());
        holder.tvMaterialType.setText(data.get(position).getMaterialTypeName());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate,tvPrice,tvCatName,tvMaterialType;

        public ViewHolder(View itemView) {
            super(itemView);

            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            tvCatName = (TextView) itemView.findViewById(R.id.tvCatName);
            tvMaterialType = (TextView) itemView.findViewById(R.id.tvMaterialType);

        }

    }
}
