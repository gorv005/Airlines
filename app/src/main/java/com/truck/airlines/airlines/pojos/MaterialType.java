package com.truck.airlines.airlines.pojos;

/**
 * Created by aditya.singh on 4/23/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MaterialType implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("material_name")
    @Expose
    private String materialName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    @Override
    public String toString() {
        return materialName;
    }
}