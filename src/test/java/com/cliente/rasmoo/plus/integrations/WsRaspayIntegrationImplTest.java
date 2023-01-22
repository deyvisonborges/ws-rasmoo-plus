package com.cliente.rasmoo.plus.integrations;

import com.cliente.rasmoo.plus.dtos.wsraspay.CustomerDto;
import com.cliente.rasmoo.plus.dtos.wsraspay.OrderDto;
import com.cliente.rasmoo.plus.integrations.impl.WsRaspayIntegrationImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class WsRaspayIntegrationImplTest {
    @Autowired
    private WsRaspayIntegrationImpl wsRaspayIntegration;
    @Test
    void createCustomerWhenDtoOK() {
        var customerDto = new CustomerDto(null, "60736940324"
                 , "deyv.borges@gmail.com", "deyvison",
                 "borges");
        wsRaspayIntegration.createCustomer(customerDto);
    }

    @Test
    void createOrderWhenDtoOK() {
        var orderDto = new OrderDto("123123", BigDecimal.ZERO, "fr23f23f", "MONTH22");
        wsRaspayIntegration.createOrder(orderDto);
    }
}
