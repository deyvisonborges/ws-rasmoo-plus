package com.cliente.rasmoo.plus.dtos.wsraspay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String customerId;
    private BigDecimal discount;
    private String id;
    private String productAcronym;
}
