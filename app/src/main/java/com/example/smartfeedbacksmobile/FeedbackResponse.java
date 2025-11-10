package com.example.smartfeedbacksmobile;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class FeedbackResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("page")
    private int page;

    @SerializedName("limit")
    private int limit;

    @SerializedName("total")
    private int total;

    @SerializedName("data")
    private List<FeedbackItem> data;

    public String getStatus() {
        return status;
    }

    public int getPage() {
        return page;
    }

    public int getLimit() {
        return limit;
    }

    public int getTotal() {
        return total;
    }

    public List<FeedbackItem> getData() {
        return data;
    }
}
