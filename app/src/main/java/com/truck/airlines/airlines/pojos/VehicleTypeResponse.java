package com.truck.airlines.airlines.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class VehicleTypeResponse implements Serializable{
    @SerializedName("vehicletypes")
    @Expose
    private List<Vehicletype> vehicletypes = null;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<Vehicletype> getVehicletypes() {
        return vehicletypes;
    }

    public void setVehicletypes(List<Vehicletype> vehicletypes) {
        this.vehicletypes = vehicletypes;
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
