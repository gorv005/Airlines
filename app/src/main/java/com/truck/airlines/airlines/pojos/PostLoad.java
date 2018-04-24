package com.truck.airlines.airlines.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gaurav.garg on 24-04-2018.
 */

public class PostLoad {
    @SerializedName("weight_id")
    @Expose
    private String weightId;
    @SerializedName("truck_type_id")
    @Expose
    private String truckTypeId;
    @SerializedName("material_type_id")
    @Expose
    private String materialTypeId;
    @SerializedName("source_pincode")
    @Expose
    private String sourcePincode;
    @SerializedName("source_city")
    @Expose
    private String sourceCity;
    @SerializedName("destination_pincode")
    @Expose
    private String destinationPincode;
    @SerializedName("destination_city")
    @Expose
    private String destinationCity;
    @SerializedName("no_of_truck")
    @Expose
    private String noOfTruck;
    @SerializedName("date")
    @Expose
    private String date;

    public String getWeightId() {
        return weightId;
    }

    public void setWeightId(String weightId) {
        this.weightId = weightId;
    }

    public String getTruckTypeId() {
        return truckTypeId;
    }

    public void setTruckTypeId(String truckTypeId) {
        this.truckTypeId = truckTypeId;
    }

    public String getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(String materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    public String getSourcePincode() {
        return sourcePincode;
    }

    public void setSourcePincode(String sourcePincode) {
        this.sourcePincode = sourcePincode;
    }

    public String getSourceCity() {
        return sourceCity;
    }

    public void setSourceCity(String sourceCity) {
        this.sourceCity = sourceCity;
    }

    public String getDestinationPincode() {
        return destinationPincode;
    }

    public void setDestinationPincode(String destinationPincode) {
        this.destinationPincode = destinationPincode;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getNoOfTruck() {
        return noOfTruck;
    }

    public void setNoOfTruck(String noOfTruck) {
        this.noOfTruck = noOfTruck;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
