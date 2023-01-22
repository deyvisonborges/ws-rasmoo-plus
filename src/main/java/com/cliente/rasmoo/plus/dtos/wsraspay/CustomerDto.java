package com.cliente.rasmoo.plus.dtos.wsraspay;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private String id;
    private String cpf;
    private String email;

//    @JsonProperty("first_name")
    private String firstName;
    private String lastName;
}
