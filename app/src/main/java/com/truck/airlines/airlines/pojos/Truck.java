package com.truck.airlines.airlines.pojos;

/**
 * Created by aditya.singh on 4/24/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Truck {
    @SerializedName("destination_city")
    @Expose
    private String destinationCity;
    @SerializedName("driver_number")
    @Expose
    private String driverNumber;
    @SerializedName("truck_type")
    @Expose
    private String truckType;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("driver_name")
    @Expose
    private String driverName;
    @SerializedName("destination_pincode")
    @Expose
    private String destinationPincode;
    @SerializedName("truck_type_id")
    @Expose
    private String truckTypeId;
    @SerializedName("source_pincode")
    @Expose
    private String sourcePincode;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("weight_id")
    @Expose
    private String weightId;
    @SerializedName("truck_number")
    @Expose
    private String truckNumber;
    @SerializedName("source_city")
    @Expose
    private String sourceCity;
    @SerializedName("truck_id")
    @Expose
    private String truckId;

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(String driverNumber) {
        this.driverNumber = driverNumber;
    }

    public String getTruckType() {
        return truckType;
    }

    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDestinationPincode() {
        return destinationPincode;
    }

    public void setDestinationPincode(String destinationPincode) {
        this.destinationPincode = destinationPincode;
    }

    public String getTruckTypeId() {
        return truckTypeId;
    }

    public void setTruckTypeId(String truckTypeId) {
        this.truckTypeId = truckTypeId;
    }

    public String getSourcePincode() {
        return sourcePincode;
    }

    public void setSourcePincode(String sourcePincode) {
        this.sourcePincode = sourcePincode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeightId() {
        return weightId;
    }

    public void setWeightId(String weightId) {
        this.weightId = weightId;
    }

    public String getTruckNumber() {
        return truckNumber;
    }

    public void setTruckNumber(String truckNumber) {
        this.truckNumber = truckNumber;
    }

    public String getSourceCity() {
        return sourceCity;
    }

    public void setSourceCity(String sourceCity) {
        this.sourceCity = sourceCity;
    }

    public String getTruckId() {
        return truckId;
    }

    public void setTruckId(String truckId) {
        this.truckId = truckId;
    }

}