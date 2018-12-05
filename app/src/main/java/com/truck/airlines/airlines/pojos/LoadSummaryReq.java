package com.truck.airlines.airlines.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoadSummaryReq implements Serializable {


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("u_id")
    @Expose
    private String uId;
    @SerializedName("source_address")
    @Expose
    private String sourceAddress;
    @SerializedName("destination_address")
    @Expose
    private String destinationAddress;
    @SerializedName("page")
    @Expose
    private String page;
    @SerializedName("perpage")
    @Expose
    private String perpage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUId() {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPerpage() {
        return perpage;
    }

    public void setPerpage(String perpage) {
        this.perpage = perpage;
    }
}
