package com.cliente.rasmoo.plus.dtos;

import com.cliente.rasmoo.plus.models.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPaymentInfoDto {
    private Long id;

    @Size(min = 16, max=16, message = "deve conter 16 caracteres")
    private Integer cardNumber;

    @Min(value = 1)
    @Max(value = 12)
    private Long cardExpirationMonth;

    private Long cardExpirationYear;

    @Size(min = 3, max = 3)
    private String cardSecurityCode;

    private BigDecimal price;
    private Number installments;
    private LocalDate dtPayment = LocalDate.now();

    @NotNull(message = "deve ser informado")
    private Long userId;
}
