package com.ons.testapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Feeds {

    @SerializedName("feeds")
    @Expose
    private List<FeedsData> feeds;

    public List<FeedsData> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<FeedsData> feeds) {
        this.feeds = feeds;
    }
}
