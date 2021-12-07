package com.example.shoppingcartmvvm.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppingcartmvvm.R;
import com.example.shoppingcartmvvm.adapters.ShopListAdapter;
import com.example.shoppingcartmvvm.databinding.FragmentShopBinding;
import com.example.shoppingcartmvvm.models.Product;

public class ShopFragment extends Fragment implements ShopListAdapter.ShopInterface {

    FragmentShopBinding fragmentShopBinding;
    private ShopListAdapter shopListAdapter;

    public ShopFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentShopBinding = FragmentShopBinding.inflate(inflater, container, false);
        return fragmentShopBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopListAdapter = new ShopListAdapter();
        fragmentShopBinding.shpRecyclerView.setAdapter(shopListAdapter);
    }

    @Override
    public void addItem(Product product) {

    }

    @Override
    public void onItemClick(Product product) {

    }
}