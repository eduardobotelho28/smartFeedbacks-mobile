package com.example.smartfeedbacksmobile;

import com.google.gson.annotations.SerializedName;

public class FeedbackItem {

    @SerializedName("form_name")
    private String formName;

    @SerializedName("client_name")
    private String clientName; // pode ser null

    @SerializedName("date")
    private String date;

    @SerializedName("reply_hash")
    private String replyHash;

    @SerializedName("form_url")
    private String formUrl;

    public String getFormName() {
        return formName;
    }

    public String getClientName() {
        return clientName;
    }

    public String getDate() {
        return date;
    }

    public String getReplyHash() {
        return replyHash;
    }

    public String getFormUrl() {
        return formUrl;
    }
}
