package com.ecommerce.order_service.order;

import com.ecommerce.order_service.orderitem.OrderItemDTO;

import java.util.List;

public class OrderDTO {

    public long orderId;
    public Long userId;
    public List<OrderItemDTO> items;
    public double totalAmount;
}
