package com.example.smartfeedbacksmobile;

public class UserUpdateRequest {

    private String firstname;
    private String lastname;
    private String email;

    public UserUpdateRequest(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
}
