package com.ons.testapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Car {

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("regNo")
    @Expose
    private String regNo;

    @SerializedName("fuelType")
    @Expose
    private String fuelType;

    @SerializedName("transmission")
    @Expose
    private String transmission;

    @SerializedName("make")
    @Expose
    private String make;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }
}
