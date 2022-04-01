package com.misa.misa.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.misa.misa.model.FavoriteApiResponse;
import com.misa.misa.repository.FavoriteRepository;

public class FavoriteViewModel extends ViewModel {

    private final FavoriteRepository favoriteRepository;

    public FavoriteViewModel() {
        favoriteRepository = new FavoriteRepository();
    }

    public LiveData<FavoriteApiResponse> getFavorites(int userId) {
        return favoriteRepository.getFavorites(userId);
    }
}
