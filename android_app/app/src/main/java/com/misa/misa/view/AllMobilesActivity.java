package com.misa.misa.view;

import static com.misa.misa.storage.LanguageUtils.loadLocale;
import static com.misa.misa.utils.Constant.PRODUCT;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

//import com.misa.misa.R;
import com.marwaeltayeb.souq.R;
import com.misa.misa.adapter.ProductAdapter;
import com.marwaeltayeb.souq.databinding.ActivityAllMobilesBinding;
import com.misa.misa.model.Product;
import com.misa.misa.storage.LoginUtils;
import com.misa.misa.viewmodel.ProductViewModel;

public class AllMobilesActivity extends AppCompatActivity implements ProductAdapter.ProductAdapterOnClickHandler{

    private ActivityAllMobilesBinding binding;
    private ProductAdapter productAdapter;
    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_mobiles);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("All Furniture");

        int userID = LoginUtils.getInstance(this).getUserInfo().getId();

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.loadMobiles("furniture", userID);

        setupRecyclerViews();

        getAllMobiles();
    }

    private void setupRecyclerViews() {
        // Mobiles
        binding.allMobilesRecyclerView.setLayoutManager(new GridLayoutManager(this, (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) ? 2 : 4));
        binding.allMobilesRecyclerView.setHasFixedSize(true);
        productAdapter = new ProductAdapter(this,this);
    }

    public void getAllMobiles() {
        // Observe the productPagedList from ViewModel
        productViewModel.productPagedList.observe(this, products -> {
            productAdapter.submitList(products);
        });

        binding.allMobilesRecyclerView.setAdapter(productAdapter);
    }

    @Override
    public void onClick(Product product) {
        Intent intent = new Intent(AllMobilesActivity.this, DetailsActivity.class);
        // Pass an object of product class
        intent.putExtra(PRODUCT, (product));
        startActivity(intent);
    }
}
