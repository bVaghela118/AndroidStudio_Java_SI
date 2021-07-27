package com.example.dailyexpensetracker;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;

    @SerializedName("user_details")
    private userModel userDetailObject;

    public userModel getUserDetailObject() {
        return userDetailObject;
    }

    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
