package com.example.dailyexpensetracker;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class userModel {
    @SerializedName("user_details")
    private List<UsersModel> userDetails;

    public List<UsersModel> getUserDetails() {
        return userDetails;
    }
}
