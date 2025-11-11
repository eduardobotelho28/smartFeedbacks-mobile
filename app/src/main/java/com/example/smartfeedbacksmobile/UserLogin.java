package com.example.smartfeedbacksmobile;

import com.google.gson.annotations.SerializedName;

public class UserLogin {

    // ---------- CAMPOS PARA ENVIO ----------
    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;


    // ---------- CAMPOS PARA RESPOSTA ----------
    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("user_id")
    private Integer userId;  // pode ser null

    @SerializedName("token")
    private String token;    // pode ser null

    @SerializedName("lastname")
    private String lastname;    // pode ser null

    @SerializedName("firstname")
    private String firstname;    // pode ser null


    // ---------- CONSTRUTOR PARA ENVIO ----------
    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }


    // ---------- GETTERS ----------
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }
}

