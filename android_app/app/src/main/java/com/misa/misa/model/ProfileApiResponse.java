package com.misa.misa.model;

import com.google.gson.annotations.SerializedName;

import org.pmml4s.data.Datetime;

public class ProfileApiResponse {

    private int id;

    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    private boolean error;
    private String message;
    private String password;
    private String token;
    @SerializedName("isAdmin")
    private boolean isAdmin;

    @SerializedName("tokens")
    private int tokens;

    @SerializedName("date")
    private String date;

    @SerializedName("details")
    private String details;

    @SerializedName("document")
    private String document;



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getDetails() {
        return details;
    }

    public int getTokens() {
        return tokens;
    }

    public String getDate() {
        return date;
    }

    public String getDocument() {
        return document;
    }
}
