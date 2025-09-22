package com.ecommerce.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", path = "/api/products")
public interface ProductClient
{

    @GetMapping("/fetch/{productId}")
    ProductDTO getProductById(@PathVariable("productId") long productId);
}
