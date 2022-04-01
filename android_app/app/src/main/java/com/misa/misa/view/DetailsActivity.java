package com.misa.misa.view;

import static com.misa.misa.storage.LanguageUtils.loadLocale;
import static com.misa.misa.utils.Constant.LOCALHOST;
import static com.misa.misa.utils.Constant.PRODUCT;
import static com.misa.misa.utils.Constant.PRODUCTID;
import static com.misa.misa.utils.Constant.PRODUCT_ID;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
//import com.misa.misa.R;
import com.marwaeltayeb.souq.R;
import com.misa.misa.adapter.ReviewAdapter;
import com.marwaeltayeb.souq.databinding.ActivityDetailsBinding;
import com.misa.misa.model.Cart;
import com.misa.misa.model.Product;
import com.misa.misa.model.Review;
import com.misa.misa.storage.LoginUtils;
import com.misa.misa.utils.RequestCallback;
import com.misa.misa.viewmodel.ReviewViewModel;
import com.misa.misa.viewmodel.ToCartViewModel;

import java.util.List;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "DetailsActivity";

    private ActivityDetailsBinding binding;
    private ReviewViewModel reviewViewModel;
    private ToCartViewModel toCartViewModel;
    private ReviewAdapter reviewAdapter;
    private List<Review> reviewList;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        reviewViewModel = ViewModelProviders.of(this).get(ReviewViewModel.class);
        toCartViewModel = ViewModelProviders.of(this).get(ToCartViewModel.class);

        binding.txtSeeAllReviews.setOnClickListener(this);
        binding.writeReview.setOnClickListener(this);
        binding.addToCart.setOnClickListener(this);
        binding.buy.setOnClickListener(this);
        binding.viewProfile.setOnClickListener(this);

        getProductDetails();

        setUpRecycleView();

        getReviewsOfProduct();
    }

    private void setUpRecycleView() {
        binding.listOfReviews.setHasFixedSize(true);
        binding.listOfReviews.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.listOfReviews.setItemAnimator(null);
    }

    private void getProductDetails() {
        // Receive the product object
        product = getIntent().getParcelableExtra(PRODUCT);


        Log.d(TAG,"isFavourite " + product.isFavourite() + " isInCart " + product.isInCart());

        // Set Custom ActionBar Layout
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.action_bar_title_layout);
        ((TextView) findViewById(R.id.action_bar_title)).setText(product.getProductName());
        binding.nameOfProduct.setText(product.getProductName());
        binding.priceOfProduct.setText(String.valueOf("Price: $" +product.getProductPrice()));
        binding.ownerOfProduct.setText(String.valueOf("Seller: " + product.getOwnerName()));

        binding.descriptionOfProduct.setText(String.valueOf("Description: " + product.getProductDescription()));
        binding.ageOfProduct.setText(String.valueOf("Age: " + "Used for " + product.getProductAge()) + " year(s)" );

        //String imageUrl = product.getProductImage().replaceAll("\\\\", "/");
        //LOCALHOST +
        String imageUrl =  product.getProductImage().replaceAll("\\\\", "/");
        Glide.with(this)
                .load(imageUrl)
                .into(binding.imageOfProduct);
    }

    private void getReviewsOfProduct() {
        reviewViewModel.getReviews(product.getProductId()).observe(this, reviewApiResponse -> {
            if (reviewApiResponse != null) {
                reviewList = reviewApiResponse.getReviewList();
                reviewAdapter = new ReviewAdapter(reviewList);
                binding.listOfReviews.setAdapter(reviewAdapter);
     }

            if(reviewList.size() == 0){
                binding.listOfReviews.setVisibility(View.GONE);
                binding.txtFirst.setVisibility(View.VISIBLE);
            }else {
                binding.listOfReviews.setVisibility(View.VISIBLE);
                binding.txtFirst.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.txtSeeAllReviews) {
            Intent allReviewIntent = new Intent(DetailsActivity.this, AllReviewsActivity.class);
            allReviewIntent.putExtra(PRODUCT_ID,product.getProductId());
            startActivity(allReviewIntent);
        } else if (view.getId() == R.id.writeReview) {
            Intent allReviewIntent = new Intent(DetailsActivity.this, WriteReviewActivity.class);
            allReviewIntent.putExtra(PRODUCT_ID,product.getProductId());
            startActivity(allReviewIntent);
        }else if(view.getId() == R.id.addToCart){
            insertToCart(() -> product.setIsInCart(true));
            Intent cartIntent = new Intent(DetailsActivity.this, CartActivity.class);
            startActivity(cartIntent);
        }else if(view.getId() == R.id.buy){
            Intent shippingIntent = new Intent(DetailsActivity.this, ShippingAddressActivity.class);
            shippingIntent.putExtra(PRODUCTID, product.getProductId());
            startActivity(shippingIntent);
        }else if(view.getId() == R.id.viewProfile){
            Intent viewIntent = new Intent(DetailsActivity.this, ViewProfileActivity.class);
            viewIntent.putExtra(PRODUCTID, product.getOwnerName());
            startActivity(viewIntent);
        }
    }

    private void insertToCart(RequestCallback callback) {
        Cart cart = new Cart(LoginUtils.getInstance(this).getUserInfo().getId(), product.getProductId(), product.getOwnerName());
        toCartViewModel.addToCart(cart, callback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getReviewsOfProduct();
    }



}
