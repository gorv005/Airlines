package com.truck.airlines.airlines.pojos;

/**
 * Created by aditya.singh on 4/25/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Loads {

    @SerializedName("load_id")
    @Expose
    private String loadId;
    @SerializedName("weight_id")
    @Expose
    private String weightId;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("truck_type_id")
    @Expose
    private String truckTypeId;
    @SerializedName("truck_type")
    @Expose
    private String truckType;
    @SerializedName("material_type_id")
    @Expose
    private String materialTypeId;
    @SerializedName("material_name")
    @Expose
    private String materialName;
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

    public String getLoadId() {
        return loadId;
    }

    public void setLoadId(String loadId) {
        this.loadId = loadId;
    }

    public String getWeightId() {
        return weightId;
    }

    public void setWeightId(String weightId) {
        this.weightId = weightId;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTruckTypeId() {
        return truckTypeId;
    }

    public void setTruckTypeId(String truckTypeId) {
        this.truckTypeId = truckTypeId;
    }

    public String getTruckType() {
        return truckType;
    }

    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }

    public String getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(String materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
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
