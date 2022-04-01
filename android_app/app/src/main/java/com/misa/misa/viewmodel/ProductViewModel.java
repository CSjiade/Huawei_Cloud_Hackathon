package com.misa.misa.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.misa.misa.model.Product;
import com.misa.misa.net.LaptopDataSourceFactory;
import com.misa.misa.net.ProductDataSourceFactory;
import com.misa.misa.net.ProductDataSource;

public class ProductViewModel extends ViewModel {


    // Create liveData for PagedList and PagedKeyedDataSource
    public LiveData<PagedList<Product>> productPagedList;

    public LiveData<PagedList<Product>> laptopPagedList;

    // Get PagedList configuration
    private static final PagedList.Config  pagedListConfig =
            (new PagedList.Config.Builder())
                    .setEnablePlaceholders(false)
                    .setPageSize(ProductDataSource.PAGE_SIZE)
                    .build();

    public void loadMobiles(String category, int userId){
        // Get our database source factory
        ProductDataSourceFactory productDataSourceFactory = new ProductDataSourceFactory(category,userId);

        // Build the paged list
        productPagedList = (new LivePagedListBuilder<>(productDataSourceFactory, pagedListConfig)).build();
    }

    public void loadLaptops(String category, int userId){
        // Get our database source factory
        LaptopDataSourceFactory laptopDataSourceFactory = new LaptopDataSourceFactory(category,userId);

        // Build the paged list
        laptopPagedList = (new LivePagedListBuilder<>(laptopDataSourceFactory, pagedListConfig)).build();
    }

    public void invalidate(){
        if(ProductDataSourceFactory.productDataSource != null) ProductDataSourceFactory.productDataSource.invalidate();
        if(LaptopDataSourceFactory.laptopDataSource!= null) LaptopDataSourceFactory.laptopDataSource.invalidate();
    }
}
