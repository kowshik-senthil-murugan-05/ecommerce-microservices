package com.ecommerce.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service", path = "/api/user/orders")
public interface OrderClient {

    @GetMapping("/{orderId}")
    OrderDTO getOrderById(@PathVariable("orderId") long orderId);
}

