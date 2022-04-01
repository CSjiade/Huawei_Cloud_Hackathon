package com.misa.misa.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.misa.misa.model.History;
import com.misa.misa.repository.ToHistoryRepository;

import okhttp3.ResponseBody;

public class ToHistoryViewModel extends ViewModel {

    private final ToHistoryRepository toHistoryRepository;

    public ToHistoryViewModel() {
        toHistoryRepository = new ToHistoryRepository();
    }

    public LiveData<ResponseBody> addToHistory(History history) {
        return toHistoryRepository.addToHistory(history);
    }
}
