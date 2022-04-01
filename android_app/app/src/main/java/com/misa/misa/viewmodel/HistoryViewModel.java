package com.misa.misa.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.misa.misa.model.Product;
import com.misa.misa.net.HistoryDataSource;
import com.misa.misa.net.HistoryDataSourceFactory;

public class HistoryViewModel extends ViewModel {

    // Create liveData for PagedList and PagedKeyedDataSource
    public LiveData<PagedList<Product>> historyPagedList;

    public void loadHistory(int userId) {
        HistoryDataSourceFactory historyDataSourceFactory = new HistoryDataSourceFactory(userId);

        // Get PagedList configuration
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(HistoryDataSource.PAGE_SIZE).build();

        // Build the paged list
        historyPagedList = (new LivePagedListBuilder<>(historyDataSourceFactory, pagedListConfig)).build();
    }

    public void invalidate(){
        if(HistoryDataSourceFactory.historyDataSource != null) HistoryDataSourceFactory.historyDataSource.invalidate();
    }


}
