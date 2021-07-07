package coba.daily.you.model.dto;

import lombok.Data;


@Data
public class CartDto {
    private Integer id;
    private Integer idProduct;
    private Long idUser;
    private Integer productQuantity;
    private String productName;
    private Double price;

    private Double subTotalPrice;
    private String pictureUrl;

}

