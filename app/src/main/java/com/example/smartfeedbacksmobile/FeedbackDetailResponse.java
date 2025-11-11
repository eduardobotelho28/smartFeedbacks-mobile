package com.example.smartfeedbacksmobile;

import com.google.gson.annotations.SerializedName;

public class FeedbackDetailResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private FeedbackDetailData data;

    public String getStatus() { return status; }
    public FeedbackDetailData getData() { return data; }
}
