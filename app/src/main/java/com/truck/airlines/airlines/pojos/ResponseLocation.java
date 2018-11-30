package com.truck.airlines.airlines.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by aditya.singh on 11/28/2018.
 */

public class ResponseLocation implements Serializable {
    @SerializedName("districts")
    @Expose
    private ArrayList<District> districts = null;
    @SerializedName("towns")
    @Expose
    private ArrayList<Town> towns = null;
    @SerializedName("states")
    @Expose
    private ArrayList<State> states = null;
    @SerializedName("status")
    @Expose
    private boolean status = false;
    @SerializedName("error")
    @Expose
    private String error;


    public ArrayList<District> getDistricts() {
        return districts;
    }

    public void setDistricts(ArrayList<District> districts) {
        this.districts = districts;
    }


    public ArrayList<Town> getTowns() {
        return towns;
    }

    public void setTowns(ArrayList<Town> towns) {
        this.towns = towns;
    }

    public ArrayList<State> getStates() {
        return states;
    }

    public void setStates(ArrayList<State> states) {
        this.states = states;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
