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
    private final HttpHeaders headers;

    public WsRaspayIntegrationImpl() {
        restTemplate = new RestTemplate();
        headers = getHttpHeaders();
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
       try {
           HttpEntity<CustomerDto> request = new HttpEntity<>(customerDto, this.headers);
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
        try {
            HttpEntity<OrderDto> request = new HttpEntity<>(orderDto, this.headers);
            var url = "http://localhost:8081/ws-raspay/v1/order";
            ResponseEntity<OrderDto> response =
                    restTemplate.exchange(url, HttpMethod.POST, request, OrderDto.class);
            return response.getBody();
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public Boolean processPayment(PaymentDto paymentDto) {
        try {
            HttpEntity<PaymentDto> request = new HttpEntity<>(paymentDto, this.headers);
            var url = "http://localhost:8081/ws-raspay/v1/paymeny/credit-card/";
            ResponseEntity<Boolean> response =
                    restTemplate.exchange(url, HttpMethod.POST, request, Boolean.class);
            return response.getBody();
        } catch (Exception ex) {
            throw ex;
        }
    }

    private static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        var credentials = "rasmooplus:r@sm00";
        String base64 = new String(Base64.encodeBase64(credentials.getBytes()));
        headers.add("Authorization", "Basic "+base64);
        return headers;
    }
}
