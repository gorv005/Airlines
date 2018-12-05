package com.truck.airlines.airlines.pojos;

/**
 * Created by aditya.singh on 4/23/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MaterialTypeResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private Boolean statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("materialtypes")
    @Expose
    private List<MaterialType> data = null;

    public Boolean getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Boolean statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MaterialType> getData() {
        return data;
    }

    public void setData(ArrayList<MaterialType> data) {
        this.data = data;
    }

}
