package com.truck.airlines.airlines.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TruckInfo implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("u_id")
    @Expose
    private String uId;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("confirmed")
    @Expose
    private String confirmed;
    @SerializedName("rejected")
    @Expose
    private String rejected;
    @SerializedName("suspended")
    @Expose
    private String suspended;
    @SerializedName("deleted")
    @Expose
    private String deleted;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("category_type_id")
    @Expose
    private String categoryTypeId;
    @SerializedName("registration_number")
    @Expose
    private String registrationNumber;
    @SerializedName("registration_certificate")
    @Expose
    private String registrationCertificate;
    @SerializedName("registration_date")
    @Expose
    private String registrationDate;
    @SerializedName("chassis_number")
    @Expose
    private String chassisNumber;
    @SerializedName("chassis_image")
    @Expose
    private String chassisImage;
    @SerializedName("engine_number")
    @Expose
    private String engineNumber;
    @SerializedName("engine_image")
    @Expose
    private String engineImage;
    @SerializedName("vehicle_class")
    @Expose
    private Object vehicleClass;
    @SerializedName("fuel_type")
    @Expose
    private String fuelType;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("fitness_upto")
    @Expose
    private String fitnessUpto;
    @SerializedName("insurance_upto")
    @Expose
    private String insuranceUpto;
    @SerializedName("fuel_norms")
    @Expose
    private String fuelNorms;
    @SerializedName("road_tax_paid_upto")
    @Expose
    private String roadTaxPaidUpto;
    @SerializedName("front_image")
    @Expose
    private String frontImage;
    @SerializedName("back_image")
    @Expose
    private String backImage;
    @SerializedName("available")
    @Expose
    private String available;
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
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("category_type_name")
    @Expose
    private String categoryTypeName;
    @SerializedName("category_name")
    @Expose
    private String categoryName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUId() {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getRejected() {
        return rejected;
    }

    public void setRejected(String rejected) {
        this.rejected = rejected;
    }

    public String getSuspended() {
        return suspended;
    }

    public void setSuspended(String suspended) {
        this.suspended = suspended;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTypeId() {
        return categoryTypeId;
    }

    public void setCategoryTypeId(String categoryTypeId) {
        this.categoryTypeId = categoryTypeId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationCertificate() {
        return registrationCertificate;
    }

    public void setRegistrationCertificate(String registrationCertificate) {
        this.registrationCertificate = registrationCertificate;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getChassisImage() {
        return chassisImage;
    }

    public void setChassisImage(String chassisImage) {
        this.chassisImage = chassisImage;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getEngineImage() {
        return engineImage;
    }

    public void setEngineImage(String engineImage) {
        this.engineImage = engineImage;
    }

    public Object getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(Object vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFitnessUpto() {
        return fitnessUpto;
    }

    public void setFitnessUpto(String fitnessUpto) {
        this.fitnessUpto = fitnessUpto;
    }

    public String getInsuranceUpto() {
        return insuranceUpto;
    }

    public void setInsuranceUpto(String insuranceUpto) {
        this.insuranceUpto = insuranceUpto;
    }

    public String getFuelNorms() {
        return fuelNorms;
    }

    public void setFuelNorms(String fuelNorms) {
        this.fuelNorms = fuelNorms;
    }

    public String getRoadTaxPaidUpto() {
        return roadTaxPaidUpto;
    }

    public void setRoadTaxPaidUpto(String roadTaxPaidUpto) {
        this.roadTaxPaidUpto = roadTaxPaidUpto;
    }

    public String getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(String frontImage) {
        this.frontImage = frontImage;
    }

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCategoryTypeName() {
        return categoryTypeName;
    }

    public void setCategoryTypeName(String categoryTypeName) {
        this.categoryTypeName = categoryTypeName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
