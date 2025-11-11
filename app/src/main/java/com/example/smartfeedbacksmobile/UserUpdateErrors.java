package com.example.smartfeedbacksmobile;

import com.google.gson.annotations.SerializedName;

public class UserUpdateErrors {

    @SerializedName("firstname")
    private String firstname;

    @SerializedName("lastname")
    private String lastname;

    @SerializedName("email")
    private String email;

    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public String getEmail() { return email; }
}
