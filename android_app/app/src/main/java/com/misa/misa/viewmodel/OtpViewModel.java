package com.misa.misa.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.misa.misa.model.Otp;
import com.misa.misa.repository.OtpRepository;

public class OtpViewModel extends ViewModel {

    private final OtpRepository otpRepository;

    public OtpViewModel() {
        otpRepository = new OtpRepository();
    }

    public LiveData<Otp> getOtpCode(String token, String email) {
        return otpRepository.getOtpCode(token,email);
    }
}
