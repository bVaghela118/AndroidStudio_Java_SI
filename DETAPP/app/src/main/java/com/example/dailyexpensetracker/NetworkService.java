package com.example.dailyexpensetracker;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NetworkService {

    @FormUrlEncoded
    @POST("register.php")
    Call<RegistrationResponse> register(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password);
}
