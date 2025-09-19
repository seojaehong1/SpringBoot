package com.example.bagunee0917.service;


import com.example.bagunee0917.domain.CartItem;
import com.example.bagunee0917.domain.Product;

import java.util.List;

public interface ShopService {
    List<Product> getProducts();
    List<CartItem> getCartItems();
    void addToCart(Long productId, int quantity);
    void removeFromCart(Long cartItemId);
}
