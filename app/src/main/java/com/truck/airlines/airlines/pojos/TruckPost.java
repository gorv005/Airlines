package com.truck.airlines.airlines.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TruckPost implements Serializable {
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
    @SerializedName("u_id")
    @Expose
    private String uId;
    @SerializedName("date")
    @Expose
    private String date;
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
    @SerializedName("created_at")
    @Expose
    private Integer createdAt;
    @SerializedName("modified_at")
    @Expose
    private Integer modifiedAt;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("modified_by")
    @Expose
    private String modifiedBy;

    public String getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(String materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
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

    public String getUId() {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Integer modifiedAt) {
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
}
