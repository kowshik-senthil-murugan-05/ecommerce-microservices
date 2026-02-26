package com.ecommerce.cart_service.controller;

import com.ecommerce.cart_service.cart.AddToCartRequestDTO;
import com.ecommerce.cart_service.cart.CartService;
import com.ecommerce.cart_service.cart.UserCartDTO;
import com.ecommerce.cart_service.exceptionhandler.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController
{

    private final CartService cartService;

    public CartController(CartService cartService)
    {
        this.cartService = cartService;
    }

    @PostMapping("/product/addToCart/{userId}")
    public ResponseEntity<APIResponse<UserCartDTO>> addProductToCart(
            @PathVariable long userId, @RequestBody AddToCartRequestDTO requestDTO)
    {
        /*
         *   Adding products to cart is tested. It is working fine.
         *   todo - Adding same product again results in creation of two rows, it should be added in same row.
         */

        UserCartDTO dto = cartService.addProductToCart(userId, requestDTO);

        return new ResponseEntity<>(
                new APIResponse<>(
                        "Product added to cart!!",
                        true,
                        dto),
                HttpStatus.CREATED
        );
    }//

    @GetMapping("/userCart/get/{userId}")
    public ResponseEntity<UserCartDTO> getCartForUserId(@PathVariable long userId){
        UserCartDTO cartForUserId = cartService.getUserCartForUserId(userId);

        return new ResponseEntity<>(
                cartForUserId,
                HttpStatus.OK
        );
    }

    @PostMapping("/cartItem/remove/{userCartId}/{productId}")
    public ResponseEntity<APIResponse<Void>> removeCartItem(@PathVariable long userCartId, @PathVariable long productId)
    {
        cartService.removeCartItem(userCartId, productId);

        return new ResponseEntity<>(
                new APIResponse<>(
                        "Cart item removed successfully!!",
                        true, null),
                HttpStatus.OK
        );
    }

    @PostMapping("/cartItem/quantity/reduce/{userCartId}/{productId}")
    public ResponseEntity<UserCartDTO> reduceCartItemQuantity(@PathVariable long userCartId, @PathVariable long productId)
    {
        UserCartDTO reducedUserCartItemQuantity = cartService.reduceUserCartItemQuantity(userCartId, productId);

        return new ResponseEntity<UserCartDTO>(
                reducedUserCartItemQuantity,
                HttpStatus.OK
        );
    }
}

