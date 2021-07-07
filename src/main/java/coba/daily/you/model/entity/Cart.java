package coba.daily.you.model.entity;

import coba.daily.you.model.dto.AddToCartDto;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = Cart.TABLE_NAME)
@Data
public class Cart extends AddToCartDto {
    public static final String TABLE_NAME = "t_cart";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME)
    @SequenceGenerator(name = TABLE_NAME, sequenceName = "t_cart_seq")

    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_product", insertable = false, updatable = false, nullable = false)
    private Product product;

    @Column(name = "id_product", nullable = false)
    private Integer idProduct;

    private Integer productQuantity;

    private Double subTotalPrice;

    @Column(name = "status")
    private String status;

    @Column(name = "id_user", nullable = true)
    private Long idUser;

}