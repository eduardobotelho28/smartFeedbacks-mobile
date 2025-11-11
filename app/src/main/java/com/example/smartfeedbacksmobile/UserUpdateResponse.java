package com.example.smartfeedbacksmobile;

import com.google.gson.annotations.SerializedName;

public class UserUpdateResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("errors")
    private UserUpdateErrors errors;

    public String getStatus() { return status; }
    public String getMessage() { return message; }
    public UserUpdateErrors getErrors() { return errors; }
}
