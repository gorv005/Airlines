package com.truck.airlines.airlines.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserProfileResponse implements Serializable{
    @SerializedName("vmsdata")
    @Expose
    private UserProfile vmsdata;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;

    public UserProfile getVmsdata() {
        return vmsdata;
    }

    public void setVmsdata(UserProfile vmsdata) {
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
