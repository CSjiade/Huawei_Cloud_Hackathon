package com.misa.misa.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.misa.misa.model.CartApiResponse;
import com.misa.misa.repository.CartRepository;

public class CartViewModel extends ViewModel {

    private final CartRepository cartRepository;

    public CartViewModel() {
        cartRepository = new CartRepository();
    }

    public LiveData<CartApiResponse> getProductsInCart(int userId) {
        return cartRepository.getProductsInCart(userId);
    }
}
