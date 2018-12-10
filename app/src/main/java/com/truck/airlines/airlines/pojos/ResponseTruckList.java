package com.truck.airlines.airlines.pojos;

/**
 * Created by aditya.singh on 4/24/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseTruckList {

    @SerializedName("vmsdata")
    @Expose
    private List<TruckInfo> vmsdata = null;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<TruckInfo> getVmsdata() {
        return vmsdata;
    }

    public void setVmsdata(List<TruckInfo> vmsdata) {
        this.vmsdata = vmsdata;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
