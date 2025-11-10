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

    //USER INFO
    @GET("user/info/{id}")
    Call<UserInfo> getUserInfo(@Header("Authorization") String token,
                               @retrofit2.http.Path("id") int userId);

    @GET("userFeedbacks/{id}")
    Call<FeedbackResponse> getUserFeedbacks(
            @Header("Authorization") String token,
            @retrofit2.http.Path("id") int userId
    );


}
