package com.misa.misa.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.misa.misa.model.ProfileApiResponse;
import com.misa.misa.model.ReviewApiResponse;
import com.misa.misa.repository.ReviewRepository;
import com.misa.misa.repository.ViewProfileRepository;


public class ViewProfileViewModel extends ViewModel {

    private final ViewProfileRepository viewprofileRepository;

    public ViewProfileViewModel( ) {
        viewprofileRepository= new ViewProfileRepository();
    }

    public LiveData<ProfileApiResponse> getProfile(String productOwner) {
        return viewprofileRepository.getProfile(productOwner);
    }
}
