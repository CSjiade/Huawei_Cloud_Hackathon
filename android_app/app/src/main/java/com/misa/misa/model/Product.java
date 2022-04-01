package com.misa.misa.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable {

    @SerializedName("user_id")
    private int userId;

    @SerializedName("name")
    private String productOwner;

    @SerializedName("description")
    private String productDescription;


    @SerializedName("age")
    private int productAge;

    @SerializedName("id")
    private int productId;
    @SerializedName("product_name")
    private String productName;
    @SerializedName("price")
    private double productPrice;
    @SerializedName("quantity")
    private int productQuantity;
    @SerializedName("supplier")
    private String productSupplier;
    @SerializedName("category")
    private String productCategory;
    @SerializedName("image")
    private String productImage;
    @SerializedName("isFavourite")
    private int isFavourite;
    @SerializedName("isInCart")
    private int isInCart;

    @SerializedName("productOwner")
    private String owner;

    // Include child Parcelable objects
    private Product mInfo;


    public Product(String productName, double productPrice, int productQuantity, String productSupplier, String productCategory,
                   int productAge, String productDescription) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productSupplier = productSupplier;
        this.productCategory = productCategory;

    }

    public Product() { }




    //new
    public int getProductUserId() {
        return userId;
    }

    //new
    public String getOwnerName() {
        return productOwner;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    //new
    public int getProductAge() {
        return productAge;
    }

    //new
    public String getProductDescription() {
        return productDescription;
    }



    public int isFavourite() {
        return isFavourite;
    }

    public int isInCart() {
        return isInCart;
    }

    public void setIsFavourite(boolean isFavourite) {
        this.isFavourite = isFavourite ? 1 : 0;
    }

    public void setIsInCart(boolean isInCart) {
        this.isInCart = isInCart ? 1 : 0;
    }




    // Write the values to be saved to the `Parcel`.
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(productOwner);
        out.writeInt(userId);
        out.writeInt(productId);
        out.writeString(productName);
        out.writeDouble(productPrice);
        out.writeInt(productQuantity);
        out.writeString(productSupplier);
        out.writeString(productCategory);
        out.writeString(productImage);
        out.writeInt(isFavourite);
        out.writeInt(isInCart);
        out.writeString(productDescription);
        out.writeInt(productAge);
        out.writeParcelable(mInfo, flags);

    }


    // Retrieve the values written into the `Parcel`.
    private Product(Parcel in) {
        productOwner = in.readString();
        userId = in.readInt();
        productId = in.readInt();
        productName = in.readString();
        productPrice = in.readDouble();
        productQuantity = in.readInt();
        productSupplier = in.readString();
        productCategory = in.readString();
        productImage = in.readString();
        isFavourite = in.readInt();
        isInCart = in.readInt();
        productDescription = in.readString();
        productAge = in.readInt();
        mInfo = in.readParcelable(Product.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Product> CREATOR
            = new Parcelable.Creator<Product>() {

        // This simply calls our new constructor and
        // passes along `Parcel`, and then returns the new object!
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
