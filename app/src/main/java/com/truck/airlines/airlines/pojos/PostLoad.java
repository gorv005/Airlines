package com.truck.airlines.airlines.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by gaurav.garg on 24-04-2018.
 */

public class PostLoad implements Serializable{
    @SerializedName("u_id")
    @Expose
    private String uId;
    @SerializedName("category_type_id")
    @Expose
    private String categoryTypeId;
    @SerializedName("material_type_id")
    @Expose
    private String materialTypeId;
    @SerializedName("material_id")
    @Expose
    private String materialId;
    @SerializedName("source_address")
    @Expose
    private String sourceAddress;
    @SerializedName("destination_address")
    @Expose
    private String destinationAddress;
    @SerializedName("lat_s")
    @Expose
    private String latS;
    @SerializedName("lng_s")
    @Expose
    private String lngS;
    @SerializedName("lat_d")
    @Expose
    private String latD;
    @SerializedName("lng_d")
    @Expose
    private String lngD;
    @SerializedName("number_of_vehicles")
    @Expose
    private String numberOfVehicles;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("total_price")
    @Expose
    private String totalPrice;
    @SerializedName("price_approved")
    @Expose
    private String priceApproved;
    @SerializedName("date")
    @Expose
    private String date;

    public String getUId() {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

    public String getCategoryTypeId() {
        return categoryTypeId;
    }

    public void setCategoryTypeId(String categoryTypeId) {
        this.categoryTypeId = categoryTypeId;
    }

    public String getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(String materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getLatS() {
        return latS;
    }

    public void setLatS(String latS) {
        this.latS = latS;
    }

    public String getLngS() {
        return lngS;
    }

    public void setLngS(String lngS) {
        this.lngS = lngS;
    }

    public String getLatD() {
        return latD;
    }

    public void setLatD(String latD) {
        this.latD = latD;
    }

    public String getLngD() {
        return lngD;
    }

    public void setLngD(String lngD) {
        this.lngD = lngD;
    }

    public String getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public void setNumberOfVehicles(String numberOfVehicles) {
        this.numberOfVehicles = numberOfVehicles;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPriceApproved() {
        return priceApproved;
    }

    public void setPriceApproved(String priceApproved) {
        this.priceApproved = priceApproved;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
