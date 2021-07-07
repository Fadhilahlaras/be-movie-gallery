package coba.daily.you.service;

import coba.daily.you.model.dto.AddToCartDto;
import coba.daily.you.model.entity.Cart;

public interface CartService {
    void saveToCart(Cart cart, AddToCartDto addToCartDto);
}

