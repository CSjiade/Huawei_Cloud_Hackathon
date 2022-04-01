package com.misa.misa.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.misa.misa.model.Favorite;
import com.misa.misa.repository.AddFavoriteRepository;
import com.misa.misa.utils.RequestCallback;

import okhttp3.ResponseBody;

public class AddFavoriteViewModel extends ViewModel {

    private final AddFavoriteRepository addFavoriteRepository;

    public AddFavoriteViewModel() {
        addFavoriteRepository = new AddFavoriteRepository();
    }

    public LiveData<ResponseBody> addFavorite(Favorite favorite, RequestCallback callback) {
        return addFavoriteRepository.addFavorite(favorite,callback);
    }
}
