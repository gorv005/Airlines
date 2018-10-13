package com.truck.airlines.airlines.pojos;

/**
 * Created by aditya.singh on 10/13/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vmsdata {

    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("otp")
    @Expose
    private String otp;


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}