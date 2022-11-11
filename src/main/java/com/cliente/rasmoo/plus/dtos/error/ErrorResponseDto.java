package com.cliente.rasmoo.plus.dtos.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponseDto {
    private String message;
    private HttpStatus httpStatus;
    private Integer statusCode;
}
