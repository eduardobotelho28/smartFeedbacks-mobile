package com.example.smartfeedbacksmobile;

import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @SerializedName("status")
    private String status;  // "ok"

    @SerializedName("user_id")
    private String userId; // vem como String no JSON

    @SerializedName("nps")
    private int nps;

    @SerializedName("csat")
    private int csat;

    @SerializedName("stars")
    private float stars; // agora aceita valores decimais

    @SerializedName("forms_count")
    private int formsCount;

    public String getStatus() {
        return status;
    }

    public String getUserId() {
        return userId;
    }

    public int getNps() {
        return nps;
    }

    public int getCsat() {
        return csat;
    }

    public float getStars() {
        return stars;
    }

    public int getFormsCount() {
        return formsCount;
    }
}
