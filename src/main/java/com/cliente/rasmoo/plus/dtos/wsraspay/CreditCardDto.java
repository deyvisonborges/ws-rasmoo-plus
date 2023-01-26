package com.cliente.rasmoo.plus.dtos.wsraspay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardDto {
    private Long ccv;
    private String documentNumber;
    private Long installment;
    private Long month;
    private Integer number;
    private Long year;
}
