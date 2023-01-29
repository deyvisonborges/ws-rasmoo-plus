package com.cliente.rasmoo.plus.controllers;

import com.cliente.rasmoo.plus.dtos.PaymentProcessDto;
import com.cliente.rasmoo.plus.repositories.UserPaymentInfoRepository;
import com.cliente.rasmoo.plus.services.PaymentProcessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class UserPaymentInfoController {
    private final PaymentProcessService paymentProcessService;

    public UserPaymentInfoController(PaymentProcessService paymentProcessService) {
        this.paymentProcessService = paymentProcessService;
    }

    @PostMapping("/process")
    public ResponseEntity<Boolean> process(@RequestBody PaymentProcessDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentProcessService.process(dto));
    }
}
