package com.truck.airlines.airlines.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoadSummaryData implements Serializable{
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("guid")
    @Expose
    private String guid;
    @SerializedName("category_type_id")
    @Expose
    private String categoryTypeId;
    @SerializedName("material_type_id")
    @Expose
    private String materialTypeId;
    @SerializedName("source_city")
    @Expose
    private String sourceCity;
    @SerializedName("source_pincode")
    @Expose
    private String sourcePincode;
    @SerializedName("source_address")
    @Expose
    private String sourceAddress;
    @SerializedName("destination_city")
    @Expose
    private String destinationCity;
    @SerializedName("destination_pincode")
    @Expose
    private String destinationPincode;
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
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("modified_at")
    @Expose
    private String modifiedAt;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("modified_by")
    @Expose
    private String modifiedBy;
    @SerializedName("category_type_name")
    @Expose
    private String categoryTypeName;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("material_type_name")
    @Expose
    private String materialTypeName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
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

    public String getSourceCity() {
        return sourceCity;
    }

    public void setSourceCity(String sourceCity) {
        this.sourceCity = sourceCity;
    }

    public String getSourcePincode() {
        return sourcePincode;
    }

    public void setSourcePincode(String sourcePincode) {
        this.sourcePincode = sourcePincode;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDestinationPincode() {
        return destinationPincode;
    }

    public void setDestinationPincode(String destinationPincode) {
        this.destinationPincode = destinationPincode;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCategoryTypeName() {
        return categoryTypeName;
    }

    public void setCategoryTypeName(String categoryTypeName) {
        this.categoryTypeName = categoryTypeName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }
}
