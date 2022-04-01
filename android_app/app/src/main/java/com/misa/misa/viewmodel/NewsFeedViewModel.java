package com.misa.misa.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.misa.misa.model.NewsFeedResponse;
import com.misa.misa.repository.NewsFeedRepository;

public class NewsFeedViewModel extends ViewModel {

    private final NewsFeedRepository newsFeedRepository;

    public NewsFeedViewModel() {
        newsFeedRepository = new NewsFeedRepository();
    }

    public LiveData<NewsFeedResponse> getPosters() {
        return newsFeedRepository.getPosters();
    }
}
