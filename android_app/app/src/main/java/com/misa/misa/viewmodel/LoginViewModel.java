package com.misa.misa.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.misa.misa.model.LoginApiResponse;
import com.misa.misa.repository.LoginRepository;

public class LoginViewModel extends ViewModel {

    private final LoginRepository loginRepository;
    public LoginViewModel() {
        loginRepository = new LoginRepository();
    }
    public LiveData<LoginApiResponse> getLoginResponseLiveData(String email, String password) {
        return loginRepository.getLoginResponseData(email, password);
    }
}
