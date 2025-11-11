package com.example.smartfeedbacksmobile;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Api {

    String BASE_URL = "https://smartfeedbacks.com.br/api/";

    //LOGIN
    @POST("login")
    Call<UserLogin> login(@Body UserLogin userLogin);

    //USER INFO
    @GET("user/info/{id}")
    Call<UserInfo> getUserInfo(@Header("Authorization") String token,
                               @retrofit2.http.Path("id") int userId);

    //GET FEEDBACKS
    @GET("userFeedbacks/{id}")
    Call<FeedbackResponse> getUserFeedbacks(
            @Header("Authorization") String token,
            @retrofit2.http.Path("id") int userId
    );

    //GET FEEDBACK INFO
    @GET("feedbackInfo/{hash}")
    Call<FeedbackDetailResponse> getFeedbackDetail(
            @Header("Authorization") String token,
            @retrofit2.http.Path("hash") String replyHash
    );

    //UPDATE USER
    @PUT("user/{id}")
    Call<UserUpdateResponse> updateUser(
            @Header("Authorization") String token,
            @retrofit2.http.Path("id") int userId,
            @Body UserUpdateRequest userUpdateRequest
    );




}
