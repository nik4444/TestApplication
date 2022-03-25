package com.ons.testapplication.network;

import com.google.gson.JsonElement;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface ApiCallInterface {

    @GET(EndPoint.GET_API_DATA)
    Observable<JsonElement> getApiData();

}
