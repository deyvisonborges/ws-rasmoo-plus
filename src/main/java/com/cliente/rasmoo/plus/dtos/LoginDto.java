package com.cliente.rasmoo.plus.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @NotBlank(message = "atributo obrigatório")
    private String username;

    @NotBlank(message = "atributo obrigatório")
    private String password;
}
