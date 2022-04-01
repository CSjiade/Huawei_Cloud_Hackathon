package com.misa.misa.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.misa.misa.model.Ordering;
import com.misa.misa.repository.OrderingRepository;

import okhttp3.ResponseBody;

public class OrderingViewModel extends ViewModel {

    private final OrderingRepository orderingRepository;

    public OrderingViewModel(  ) {
        orderingRepository = new OrderingRepository();
    }

    public LiveData<ResponseBody> orderProduct(Ordering ordering) {
        return orderingRepository.orderProduct(ordering);
    }
}
