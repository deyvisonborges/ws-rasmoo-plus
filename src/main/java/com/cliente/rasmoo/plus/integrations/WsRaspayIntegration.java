package com.cliente.rasmoo.plus.integrations;

import com.cliente.rasmoo.plus.dtos.wsraspay.CustomerDto;
import com.cliente.rasmoo.plus.dtos.wsraspay.OrderDto;
import com.cliente.rasmoo.plus.dtos.wsraspay.PaymentDto;

public interface WsRaspayIntegration {
    CustomerDto createCustomer(CustomerDto customerDto);
    OrderDto createOrder(OrderDto orderDto);
    Boolean processPayment(PaymentDto paymentDto);
}
