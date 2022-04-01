package com.misa.misa.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.misa.misa.model.OrderApiResponse;
import com.misa.misa.repository.OrderRepository;

public class OrderViewModel extends ViewModel {

    private final OrderRepository orderRepository;

    public OrderViewModel() {
        orderRepository = new OrderRepository();
    }

    public LiveData<OrderApiResponse> getOrders(int userId) {
        return orderRepository.getOrders(userId);
    }
}

