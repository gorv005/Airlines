package com.truck.airlines.airlines.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserProfileReq implements Serializable {


    @SerializedName("u_id")
    @Expose
    private String id;
    public UserProfileReq() {
    }
    public UserProfileReq(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
