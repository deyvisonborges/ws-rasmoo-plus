package com.cliente.rasmoo.plus.exceptions;

public class BadRequestException extends RuntimeException  {
    public BadRequestException(String message) {
        super(message);
    }
}
