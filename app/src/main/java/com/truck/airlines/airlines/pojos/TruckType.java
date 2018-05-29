package com.truck.airlines.airlines.pojos;

/**
 * Created by aditya.singh on 4/23/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TruckType implements Serializable{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("truck_type")
    @Expose
    private String truckType;

    public TruckType(String truckType) {
        this.truckType = truckType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTruckType() {
        return truckType;
    }

    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }
    @Override
    public String toString() {
        return truckType;
    }
}