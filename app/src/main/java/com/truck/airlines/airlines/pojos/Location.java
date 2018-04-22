package com.truck.airlines.airlines.pojos;

/**
 * Created by Ady on 4/22/2018.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("PostOffice")
    @Expose
    private List<PostOffice> postOffice = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Location() {
    }

    /**
     *
     * @param message
     * @param postOffice
     * @param status
     */
    public Location(String message, String status, List<PostOffice> postOffice) {
        super();
        this.message = message;
        this.status = status;
        this.postOffice = postOffice;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PostOffice> getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(List<PostOffice> postOffice) {
        this.postOffice = postOffice;
    }

}