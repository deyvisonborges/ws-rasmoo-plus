package com.cliente.rasmoo.plus.integrations.impl;

import com.cliente.rasmoo.plus.dtos.wsraspay.CustomerDto;
import com.cliente.rasmoo.plus.dtos.wsraspay.OrderDto;
import com.cliente.rasmoo.plus.dtos.wsraspay.PaymentDto;
import com.cliente.rasmoo.plus.integrations.WsRaspayIntegration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class WsRaspayIntegrationImpl implements WsRaspayIntegration {
    private RestTemplate restTemplate;

    public WsRaspayIntegrationImpl() {
        restTemplate = new RestTemplate();
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        return null;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        return null;
    }

    @Override
    public Boolean processPayment(PaymentDto paymentDto) {
        return null;
    }
}
