package com.ecommerce.cart_service.cart;

import com.ecommerce.cart_service.util.PageDetails;

public interface CartService
{
    UserCartDTO addProductToCart(long userId, AddToCartRequestDTO dto);

    PageDetails<UserCartDTO> getAllCarts(int pageNumber, int pageSize, String sortBy, String sortOrder);

    UserCartDTO getUserCartForUserId(long userId);

    UserCartDTO reduceUserCartItemQuantity(long userCartId, long productId);

    void removeCartItem(long userCartId, long productId);
}
