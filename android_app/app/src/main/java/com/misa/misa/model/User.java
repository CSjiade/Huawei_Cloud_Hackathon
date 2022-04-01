package com.misa.misa.model;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private boolean isAdmin;
    private String details;
    private int tokens;

    public User(String name, String email, String password, String details) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.details = details;
    }

    public User(int id,String name, String email, String password,  boolean isAdmin
                ,String details, int tokens
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.tokens = tokens;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDetails() {
        return details;
    }

    public int getTokens() {
        return tokens;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

}
