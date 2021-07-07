package coba.daily.you.model.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDetailsDto {
    private Integer id;
    private Date tanggalCi;
    private Integer tipePembayaran;
    private Double totalHargalCi;
    private Date batasTanggalPembayaran;
    private Enum statusPesanan;

    private List<OrderItemsDto> items;
}
