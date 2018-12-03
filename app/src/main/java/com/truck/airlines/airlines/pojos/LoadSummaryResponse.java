package com.truck.airlines.airlines.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LoadSummaryResponse implements Serializable {
    @SerializedName("vmsdata")
    @Expose
    private List<LoadSummaryData> vmsdata = null;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<LoadSummaryData> getVmsdata() {
        return vmsdata;
    }

    public void setVmsdata(List<LoadSummaryData> vmsdata) {
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
