package com.misa.misa.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.misa.misa.net.RetrofitClient;
import com.misa.misa.model.OrderApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderRepository {

    private static final String TAG = OrderRepository.class.getSimpleName();

    public LiveData<OrderApiResponse> getOrders(int userId) {
        final MutableLiveData<OrderApiResponse> mutableLiveData = new MutableLiveData<>();
        RetrofitClient.getInstance().getApi().getOrders(userId).enqueue(new Callback<OrderApiResponse>() {
            @Override
            public void onResponse(Call<OrderApiResponse> call, Response<OrderApiResponse> response) {
                Log.d("onResponse", "" + response.code());

                OrderApiResponse responseBody = response.body();

                if (response.body() != null) {
                    mutableLiveData.setValue(responseBody);
                }
            }

            @Override
            public void onFailure(Call<OrderApiResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });


        return mutableLiveData;
    }


}