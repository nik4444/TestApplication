package com.ons.testapplication.repository;

import com.google.gson.JsonElement;
import com.ons.testapplication.network.ApiCallInterface;

import io.reactivex.Observable;

public class Repository {

    private final ApiCallInterface apiCallInterface;

    public Repository(ApiCallInterface apiCallInterface) {
        this.apiCallInterface = apiCallInterface;
    }

    public Observable<JsonElement> getApiData() {
        return apiCallInterface.getApiData();
    }

}
