package com.misa.misa.model;

import com.google.gson.annotations.SerializedName;

public class History {

    @SerializedName("userId")
    private int userId;
    @SerializedName("productId")
    private int productId;
    @SerializedName("name")
    private String name;

    public History(int userId, int productId, String name) {
        this.userId = userId;
        this.productId = productId;
        this.name = name;

    }
}
