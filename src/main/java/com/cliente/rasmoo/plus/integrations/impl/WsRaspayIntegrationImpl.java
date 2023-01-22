package com.cliente.rasmoo.plus.integrations.impl;

import com.cliente.rasmoo.plus.dtos.wsraspay.CustomerDto;
import com.cliente.rasmoo.plus.dtos.wsraspay.OrderDto;
import com.cliente.rasmoo.plus.dtos.wsraspay.PaymentDto;
import com.cliente.rasmoo.plus.integrations.WsRaspayIntegration;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
       try {
           var headers = getHttpHeaders();
           HttpEntity<CustomerDto> request = new HttpEntity<>(customerDto, headers);
           var url = "http://localhost:8081/ws-raspay/v1/customer";
           ResponseEntity<CustomerDto> response =
                   restTemplate.exchange(url, HttpMethod.POST, request, CustomerDto.class);
           return response.getBody();
       } catch (Exception ex) {
           throw ex;
       }
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        return null;
    }

    @Override
    public Boolean processPayment(PaymentDto paymentDto) {
        return null;
    }

    private static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        var credentials = "rasmooplus:r@sm00";
        String base64 = new String(Base64.encodeBase64(credentials.getBytes()));
        headers.add("Authorization", "Basic "+base64);
        return headers;
    }
}
