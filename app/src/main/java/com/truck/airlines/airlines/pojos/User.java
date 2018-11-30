package com.truck.airlines.airlines.pojos;

/**
 * Created by aditya.singh on 4/21/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("transport_name")
    @Expose
    private String transportName;
    @SerializedName("fname")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("phone1")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("address")
    @Expose
    private String address1;

    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("district")
    @Expose
    private String distric;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("pan")
    @Expose
    private String panNo;
    @SerializedName("usertype")
    @Expose
    private String userType;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("license_number")
    @Expose
    private String licenseNumber;


    public String getTransportName() {
        return transportName;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistric() {
        return distric;
    }

    public void setDistric(String distric) {
        this.distric = distric;
    }

    public String getCity() {
        return city;
    }

    public void setTown(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }


    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPincode() {
        return pincode;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}
