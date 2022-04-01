package com.misa.misa.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.misa.misa.model.Image;
import com.misa.misa.repository.UserImageRepository;

public class UserImageViewModel extends ViewModel {

    private final UserImageRepository userImageRepository;

    public UserImageViewModel() {
        userImageRepository = new UserImageRepository();
    }

    public LiveData<Image> getUserImage(int userId) {
        return userImageRepository.getUserImage(userId);
    }
}
