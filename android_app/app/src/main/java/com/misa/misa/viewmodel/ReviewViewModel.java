package com.misa.misa.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.misa.misa.model.ReviewApiResponse;
import com.misa.misa.repository.ReviewRepository;

public class ReviewViewModel extends ViewModel {

    private final ReviewRepository reviewRepository;

    public ReviewViewModel(  ) {
        reviewRepository = new ReviewRepository();
    }

    public LiveData<ReviewApiResponse> getReviews(int productId) {
        return reviewRepository.getReviews(productId);
    }
}
