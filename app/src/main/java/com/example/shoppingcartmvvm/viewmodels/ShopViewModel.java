package com.example.shoppingcartmvvm.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.shoppingcartmvvm.models.Product;
import com.example.shoppingcartmvvm.repositories.ShopRepo;

import java.util.List;

public class ShopViewModel extends ViewModel {

    ShopRepo shopRepo = new ShopRepo();

    public LiveData<List<Product>> getProducts() {
        return shopRepo.getProducts();
    }
}
