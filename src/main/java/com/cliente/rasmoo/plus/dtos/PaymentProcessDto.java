package com.cliente.rasmoo.plus.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentProcessDto {
    @NotBlank(message = "deve ser informado")
    private String productKey;
    private BigDecimal discount;

    @NotNull(message = "dados do pagamentos devem ser informados")
    @JsonProperty("userPaymentInfo")
    private UserPaymentInfoDto userPaymentInfoDto;

}
