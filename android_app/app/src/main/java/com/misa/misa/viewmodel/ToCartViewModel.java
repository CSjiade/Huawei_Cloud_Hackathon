package com.misa.misa.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.misa.misa.model.Cart;
import com.misa.misa.repository.ToCartRepository;
import com.misa.misa.utils.RequestCallback;

import okhttp3.ResponseBody;

public class ToCartViewModel extends ViewModel {

    private final ToCartRepository toCartRepository;

    public ToCartViewModel() {
        toCartRepository = new ToCartRepository();
    }

    public LiveData<ResponseBody> addToCart(Cart cart, RequestCallback callback) {
        return toCartRepository.addToCart(cart, callback);
    }
}
