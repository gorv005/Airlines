package com.truck.airlines.airlines.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RequestAddTruck implements Serializable {

    @SerializedName("category_type_id")
    @Expose
    public String categoryTypeId;
    @SerializedName("u_id")
    @Expose
    public String uId;
    @SerializedName("registration_number")
    @Expose
    public String registrationNumber;
    @SerializedName("registration_certificate")
    @Expose
    public String registrationCertificate;
    @SerializedName("chassis_number")
    @Expose
    public String chassisNumber;
    @SerializedName("chassis_image")
    @Expose
    public String chassisImage;
    @SerializedName("engine_number")
    @Expose
    public String engineNumber;
    @SerializedName("engine_image")
    @Expose
    public String engineImage;
    @SerializedName("fuel_type")
    @Expose
    public String fuelType;
    @SerializedName("fuel_norms")
    @Expose
    public String fuelNorms;
    @SerializedName("fitness_upto")
    @Expose
    public String fitnessUpto;
    @SerializedName("model")
    @Expose
    public String model;
    @SerializedName("insurance_upto")
    @Expose
    public String insuranceUpto;
    @SerializedName("road_tax_paid_upto")
    @Expose
    public String roadTaxPaidUpto;
    @SerializedName("front_image")
    @Expose
    public String frontImage;
    @SerializedName("back_image")
    @Expose
    public String backImage;
    @SerializedName("registration_date")
    @Expose
    public String registrationDate;
    @SerializedName("created_at")
    @Expose
    public Integer createdAt;
    @SerializedName("modified_at")
    @Expose
    public Integer modifiedAt;
    @SerializedName("created_by")
    @Expose
    public String createdBy;
    @SerializedName("modified_by")
    @Expose
    public String modifiedBy;

    public RequestAddTruck() {
    }

    public RequestAddTruck(String categoryTypeId, String uId, String registrationNumber, String registrationCertificate, String chassisNumber, String chassisImage, String engineNumber, String engineImage, String fuelType, String fuelNorms, String fitnessUpto, String model, String insuranceUpto, String roadTaxPaidUpto, String frontImage, String backImage, String registrationDate, Integer createdAt, Integer modifiedAt, String createdBy, String modifiedBy) {
        super();
        this.categoryTypeId = categoryTypeId;
        this.uId = uId;
        this.registrationNumber = registrationNumber;
        this.registrationCertificate = registrationCertificate;
        this.chassisNumber = chassisNumber;
        this.chassisImage = chassisImage;
        this.engineNumber = engineNumber;
        this.engineImage = engineImage;
        this.fuelType = fuelType;
        this.fuelNorms = fuelNorms;
        this.fitnessUpto = fitnessUpto;
        this.model = model;
        this.insuranceUpto = insuranceUpto;
        this.roadTaxPaidUpto = roadTaxPaidUpto;
        this.frontImage = frontImage;
        this.backImage = backImage;
        this.registrationDate = registrationDate;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
    }

    public String getCategoryTypeId() {
        return categoryTypeId;
    }

    public void setCategoryTypeId(String categoryTypeId) {
        this.categoryTypeId = categoryTypeId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
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

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getFuelNorms() {
        return fuelNorms;
    }

    public void setFuelNorms(String fuelNorms) {
        this.fuelNorms = fuelNorms;
    }

    public String getFitnessUpto() {
        return fitnessUpto;
    }

    public void setFitnessUpto(String fitnessUpto) {
        this.fitnessUpto = fitnessUpto;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getInsuranceUpto() {
        return insuranceUpto;
    }

    public void setInsuranceUpto(String insuranceUpto) {
        this.insuranceUpto = insuranceUpto;
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

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
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
