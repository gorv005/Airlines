package com.truck.airlines.airlines.pojos;

/**
 * Created by aditya.singh on 10/13/2018.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseOTP {

    @SerializedName("vmsdata")
    @Expose
    private Vmsdata vmsdata;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;
    @SerializedName("message")
    @Expose
    private String message;

    public Vmsdata getVmsdata() {
        return vmsdata;
    }

    public void setVmsdata(Vmsdata vmsdata) {
        this.vmsdata = vmsdata;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
