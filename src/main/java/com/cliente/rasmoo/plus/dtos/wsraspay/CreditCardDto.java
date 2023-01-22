package com.cliente.rasmoo.plus.dtos.wsraspay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardDto {
    private Long ccv;
    private String documentNumber;
    private Long installment;
    private Long month;
    private String number;
    private Long year;
}
