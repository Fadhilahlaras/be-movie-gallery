package coba.daily.you.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartItemsDto {
    private List<AddToCartDto> addToCart;
}
