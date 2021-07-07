package coba.daily.you.model.dto;

import lombok.Data;

@Data
public class AddToCartDto {
    private Integer id;
    private Integer idProduct;
    private Integer productQuantity;
    private Long idUser;
}
