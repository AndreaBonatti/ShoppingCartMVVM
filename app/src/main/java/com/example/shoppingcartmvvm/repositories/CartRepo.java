package com.example.shoppingcartmvvm.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppingcartmvvm.models.CartItem;
import com.example.shoppingcartmvvm.models.Product;

import java.util.ArrayList;
import java.util.List;

public class CartRepo {
    private MutableLiveData<List<CartItem>> mutableCart = new MutableLiveData<>();

    public LiveData<List<CartItem>> getCart() {
        if (mutableCart.getValue() == null) {
            initCart();
        }
        return mutableCart;
    }

    private void initCart() {
        mutableCart.setValue(new ArrayList<>());
    }

    public boolean addItemToCart(Product product) {
        if (mutableCart.getValue() == null) {
            initCart();
        }

        // controllo il carrello prima di aggiungere un prodotto
        // se esiste già il prodotto allora e ne ho già 5 non lo aggiungo
        // se esiste e ne ho meno di 5 lo aggiungo
        // se non esiste lo aggiungo
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        for(CartItem cartItem: cartItemList){
            if(cartItem.getProduct().getId().equals(product.getId())){
                if(cartItem.getQuantity() == 5){
                    return false;
                }

                int index = cartItemList.indexOf(cartItem);
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItemList.set(index, cartItem);

                mutableCart.setValue(cartItemList);

                return true;
            }
        }

        CartItem cartItem = new CartItem(product, 1);
        cartItemList.add(cartItem);
        mutableCart.setValue(cartItemList);
        return true;
    }
}
