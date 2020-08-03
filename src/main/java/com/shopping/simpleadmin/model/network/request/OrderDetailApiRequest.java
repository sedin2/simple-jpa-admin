package com.shopping.simpleadmin.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailApiRequest {

    private Long id;

    private String status;

    private LocalDate arrivalDate;

    private Integer quantity;

    private BigDecimal totalPrice;

    private Long itemId;

    private Long orderGroupId;
}
