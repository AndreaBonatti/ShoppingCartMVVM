package com.example.shoppingcartmvvm.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.example.shoppingcartmvvm.R;
import com.example.shoppingcartmvvm.adapters.ShopListAdapter;
import com.example.shoppingcartmvvm.databinding.FragmentShopBinding;
import com.example.shoppingcartmvvm.models.Product;
import com.example.shoppingcartmvvm.viewmodels.ShopViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ShopFragment extends Fragment implements ShopListAdapter.ShopInterface {

    private static final String TAG = "ShopFragment";
    FragmentShopBinding fragmentShopBinding;
    private ShopListAdapter shopListAdapter;
    private ShopViewModel shopViewModel;
    private NavController navController;

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

        shopListAdapter = new ShopListAdapter(this);
        fragmentShopBinding.shopRecyclerView.setAdapter(shopListAdapter);
        fragmentShopBinding.shopRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        fragmentShopBinding.shopRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL));

        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        shopViewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                shopListAdapter.submitList(products);
            }
        });

        navController = Navigation.findNavController(view);
    }

    @Override
    public void addItem(Product product) {
//        Log.d(TAG, "addItem: " + product.toString());
        boolean isAdded = shopViewModel.addItemToCart(product);
//        Log.d(TAG, "addItem: " + product.getName() + " " + isAdded);
        if (isAdded) {
            Snackbar.make(requireView(), product.getName() + " added to cart.", Snackbar.LENGTH_LONG)
                    .setAction("Checkout", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            navController.navigate(R.id.action_shopFragment_to_cartFragment);
                        }
                    })
                    .show();
        } else {
            Snackbar.make(requireView(), "Already have the max quantity in cart.", Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void onItemClick(Product product) {
//        Log.d(TAG, "onItemClick: " + product.toString());
        shopViewModel.setProduct(product);
        navController.navigate(R.id.action_shopFragment_to_productDetailFragment);
    }
}