package com.ons.testapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;



public class FeedsData {

    @SerializedName("car")
    @Expose
    private Car car;

    @SerializedName("features")
    @Expose
    private List<Features> features;

    @SerializedName("offers")
    @Expose
    private List<Offers> offers;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Features> getFeatures() {
        return features;
    }

    public void setFeatures(List<Features> features) {
        this.features = features;
    }

    public List<Offers> getOffers() {
        return offers;
    }

    public void setOffers(List<Offers> offers) {
        this.offers = offers;
    }
}
