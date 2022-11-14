package com.cliente.rasmoo.plus.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionTypeDto {
    private Long id;
    @NotBlank(message = "campo name não pode ser nulo ou vazio")
    @Size(min = 5, max = 30, message = "campo name deve ter tamanho entre 5 e 30")
    private String name;

    @Max(value = 12, message = "campo access_month não pode ser maior que 12")
    private Long accessMonth;

    @NotNull(message = "campo price não pode ser nulo")
    private BigDecimal price;

    @NotBlank(message = "campo product_key não pode ser nulo ou vazio")
    @Size(min = 5, max = 15, message = "campo product_key deve ter tamanho entre 5 e 15")
    private String productKey;
}
