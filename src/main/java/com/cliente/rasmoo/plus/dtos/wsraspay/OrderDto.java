package com.cliente.rasmoo.plus.dtos.wsraspay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String customerId;
    private Long discount;
    private String id;
    private String productAcronym;
}
