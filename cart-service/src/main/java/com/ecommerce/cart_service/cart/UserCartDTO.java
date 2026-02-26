package com.ecommerce.cart_service.cart;


import com.ecommerce.cart_service.cartitem.CartItemDTO;

import java.util.List;

public class UserCartDTO
{
    public long cartId;
    public long userId;
    public List<CartItemDTO> cartItemDTOS;
    public double totalPrice;
}
