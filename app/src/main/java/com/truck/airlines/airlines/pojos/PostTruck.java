package com.truck.airlines.airlines.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gaurav.garg on 24-04-2018.
 */

public class PostTruck {
    @SerializedName("weight_id")
    @Expose
    private String weightId;
    @SerializedName("truck_type_id")
    @Expose
    private String truckTypeId;
    @SerializedName("truck_number")
    @Expose
    private String truckNumber;
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
    @SerializedName("driver_number")
    @Expose
    private String driverNumber;
    @SerializedName("driver_name")
    @Expose
    private String driverName;
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

    public String getTruckNumber() {
        return truckNumber;
    }

    public void setTruckNumber(String truckNumber) {
        this.truckNumber = truckNumber;
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

    public String getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(String driverNumber) {
        this.driverNumber = driverNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
