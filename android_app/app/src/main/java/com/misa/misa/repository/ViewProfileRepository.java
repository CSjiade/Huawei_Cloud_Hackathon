package com.misa.misa.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.misa.misa.model.ProfileApiResponse;
import com.misa.misa.net.RetrofitClient;
import com.misa.misa.model.ReviewApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class ViewProfileRepository {

    private static final String TAG = ViewProfileRepository.class.getSimpleName();

    public LiveData<ProfileApiResponse> getProfile(String productOwner) {
        final MutableLiveData<ProfileApiResponse> mutableLiveData = new MutableLiveData<>();
        RetrofitClient.getInstance().getApi().getProfile(productOwner).enqueue(new Callback<ProfileApiResponse>() {
            @Override
            public void onResponse(Call<ProfileApiResponse> call, Response<ProfileApiResponse> response) {
                ProfileApiResponse responseBody = response.body();
                if (response.body() != null) {
                    Log.d("inijh",responseBody.getDate());
                    mutableLiveData.setValue(responseBody);
                }
            }

            @Override
            public void onFailure(Call<ProfileApiResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());

            }

        });
        return mutableLiveData;
    }

}
