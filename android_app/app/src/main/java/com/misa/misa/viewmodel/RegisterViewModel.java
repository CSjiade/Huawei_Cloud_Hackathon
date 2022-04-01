package com.misa.misa.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.misa.misa.model.RegisterApiResponse;
import com.misa.misa.model.User;
import com.misa.misa.repository.RegisterRepository;

public class RegisterViewModel extends ViewModel {

    private final RegisterRepository registerRepository;

    public RegisterViewModel() {
        registerRepository = new RegisterRepository();
    }

    public LiveData<RegisterApiResponse> getRegisterResponseLiveData(User user) {
        return registerRepository.getRegisterResponseData(user);
    }
}
