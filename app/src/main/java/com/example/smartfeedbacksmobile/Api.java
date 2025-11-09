package com.example.smartfeedbacksmobile;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Api {

    String BASE_URL = "https://smartfeedbacks.com.br/api/";

    //LOGIN
    @POST("login")
    Call<UserLogin> login(@Body UserLogin userLogin);

}
