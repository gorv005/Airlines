package com.truck.airlines.airlines.pojos;

/**
 * Created by aditya.singh on 4/23/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginUser {



    @SerializedName("confirmed")
    @Expose
    private String confirmed;
    @SerializedName("ut_id")
    @Expose
    private String utId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ut_name")
    @Expose
    private String utName;
    @SerializedName("isauthentication")
    @Expose
    private String isauthentication;
    @SerializedName("available")
    @Expose
    private String available;
    @SerializedName("u_id")
    @Expose
    private String uId;
    @SerializedName("deleted")
    @Expose
    private String deleted;
    @SerializedName("suspended")
    @Expose
    private String suspended;
    @SerializedName("truck_id")
    @Expose
    private String truckId;
    @SerializedName("pan")
    @Expose
    private String pan;

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getUtId() {
        return utId;
    }

    public void setUtId(String utId) {
        this.utId = utId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUtName() {
        return utName;
    }

    public void setUtName(String utName) {
        this.utName = utName;
    }

    public String getIsauthentication() {
        return isauthentication;
    }

    public void setIsauthentication(String isauthentication) {
        this.isauthentication = isauthentication;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getUId() {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getSuspended() {
        return suspended;
    }

    public void setSuspended(String suspended) {
        this.suspended = suspended;
    }

    public String getTruckId() {
        return truckId;
    }

    public void setTruckId(String truckId) {
        this.truckId = truckId;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

}