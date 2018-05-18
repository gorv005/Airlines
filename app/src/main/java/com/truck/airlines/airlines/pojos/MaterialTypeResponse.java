package com.truck.airlines.airlines.pojos;

/**
 * Created by aditya.singh on 4/23/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class MaterialTypeResponse implements Serializable {

    @SerializedName("status_code")
    @Expose
    private String statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ArrayList<MaterialType> data = null;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<MaterialType> getData() {
        return data;
    }

    public void setData(ArrayList<MaterialType> data) {
        this.data = data;
    }

}
