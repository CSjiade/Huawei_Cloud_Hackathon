package com.misa.misa.view;

import static com.misa.misa.storage.LanguageUtils.loadLocale;
import static com.misa.misa.utils.Constant.PRODUCT_ID;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

//import com.misa.misa.R;
import com.marwaeltayeb.souq.R;
import com.misa.misa.adapter.ReviewAdapter;
import com.marwaeltayeb.souq.databinding.ActivityAllReviewsBinding;
import com.misa.misa.model.Review;
import com.misa.misa.viewmodel.ReviewViewModel;

import java.util.List;

public class AllReviewsActivity extends AppCompatActivity {

    private ActivityAllReviewsBinding binding;
    private ReviewViewModel reviewViewModel;
    private ReviewAdapter reviewAdapter;
    private List<Review> reviewList;
    private int productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_reviews);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.reviews));

        reviewViewModel = ViewModelProviders.of(this).get(ReviewViewModel.class);

        Intent intent = getIntent();
        productId = intent.getIntExtra(PRODUCT_ID, 0);

        setUpRecycleView();

        getReviewsOfProduct();
    }

    private void setUpRecycleView() {
        binding.allReviewsList.setHasFixedSize(true);
        binding.allReviewsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void getReviewsOfProduct() {
        reviewViewModel.getReviews(productId).observe(this, reviewApiResponse -> {
            if (reviewApiResponse != null) {
                binding.rateProduct.setRating(reviewApiResponse.getAverageReview());
                binding.rateNumber.setText(reviewApiResponse.getAverageReview() + getString(R.string.highestNumber));
                reviewList = reviewApiResponse.getReviewList();
                reviewAdapter = new ReviewAdapter(reviewList);
                binding.allReviewsList.setAdapter(reviewAdapter);
            }
        });
    }
}
