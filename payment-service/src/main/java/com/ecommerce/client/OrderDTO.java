package com.ecommerce.client;

import java.util.List;

public class OrderDTO {
    public long orderId;
    public long userId;
    public double totalAmount;
    public List<OrderItemDTO> items;
}

