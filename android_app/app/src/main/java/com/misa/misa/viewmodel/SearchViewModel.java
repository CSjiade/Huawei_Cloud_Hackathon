package com.misa.misa.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.misa.misa.model.ProductApiResponse;
import com.misa.misa.repository.SearchRepository;

public class SearchViewModel  extends ViewModel {

    private final SearchRepository searchRepository;

    public SearchViewModel(  ) {
        searchRepository = new SearchRepository();
    }


    public LiveData<ProductApiResponse> getProductsBySearch(String keyword, int userId) {
        return searchRepository.getResponseDataBySearch(keyword, userId);
    }
}
