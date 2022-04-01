package com.misa.misa.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.misa.misa.model.Review;
import com.misa.misa.repository.WriteReviewRepository;

import okhttp3.ResponseBody;

public class WriteReviewViewModel extends ViewModel {

    private final WriteReviewRepository writeReviewRepository;

    public WriteReviewViewModel() {
        writeReviewRepository = new WriteReviewRepository();
    }

    public LiveData<ResponseBody> writeReview(Review review) {
        return writeReviewRepository.writeReview(review);
    }
}
