package com.misa.misa.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.misa.misa.repository.FromCartRepository;
import com.misa.misa.utils.RequestCallback;

import okhttp3.ResponseBody;

public class FromCartViewModel extends ViewModel {

    private final FromCartRepository fromCartRepository;

    public FromCartViewModel(  ) {
        fromCartRepository = new FromCartRepository();
    }

    public LiveData<ResponseBody> removeFromCart(int userId, int productId, RequestCallback callback) {
        return fromCartRepository.removeFromCart(userId, productId, callback);
    }
}
