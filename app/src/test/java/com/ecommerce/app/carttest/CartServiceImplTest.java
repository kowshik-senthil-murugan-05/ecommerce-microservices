package com.ecommerce.app.carttest;

import com.ecommerce.app.cart.*;
import com.ecommerce.app.cartitem.CartItem;
import com.ecommerce.app.cartitem.CartItemDTO;
import com.ecommerce.app.product.Product;
import com.ecommerce.app.product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartServiceImplTest
{

    @Mock
    private UserCartRepo userCartRepo;

    @Mock
    private ProductService productService;

    @InjectMocks
    private CartServiceImpl cartService;

    private Product product;
    private UserCart userCart;
    private AddToCartRequestDTO addToCartRequestDTO;

    private CartItem cartItem;

    @BeforeEach
    void setup()
    {
        //testAddProductToCart_WhenProductExistsAndCartExists_AddsProduct()

        product = new Product();
        product.setId(1L);
        product.setProductName("Product A");
        product.setFinalPrice(100);

        userCart = new UserCart();
        userCart.setId(1L);
        userCart.setUserId(10);
        userCart.setCartItems(new ArrayList<>());

        addToCartRequestDTO = new AddToCartRequestDTO();
        addToCartRequestDTO.productId = 1;
        addToCartRequestDTO.productQuantity = 2;

        //testReduceUserCartItemQuantity_WhenQuantityGreaterThanOne_DecrementsQuantity()
//        userCart = new UserCart();
//        userCart.setId(1L);
//        userCart.setUserId(10);
//        userCart.setCartItems(new ArrayList<>());
//
//        cartItem = new CartItem();
//        cartItem.setProductId(1L);
//        cartItem.setProductName("Product A");
//        cartItem.setDescription("Description");
//        cartItem.setProductQuantity(3);
//        cartItem.setProductPrice(100);
//        cartItem.setCurCartItemPrice(300);
//
//        userCart.getCartItems().add(cartItem);
    }

    @Test
    void testAddProductToCart_WhenProductExistsAndCartExists_AddsProduct()
    {
        when(productService.getProductObjForProductId(1L)).thenReturn(Optional.of(product));
        when(userCartRepo.findByUserId(10)).thenReturn(Optional.of(userCart));
        when(userCartRepo.save(any(UserCart.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserCartDTO dto = cartService.addProductToCart(10L, addToCartRequestDTO);

        assertEquals(10L, dto.userId);
        assertEquals(1, dto.cartItemDTOS.size());
        assertEquals(200.0, dto.totalPrice);

        System.out.println("userId -> " + dto.userId);
        System.out.println("cart items size -> " + dto.cartItemDTOS.size());
        System.out.println("Total price -> " + dto.totalPrice);

        verify(productService, times(1)).getProductObjForProductId(1L);
        verify(userCartRepo, times(1)).findByUserId(10L);
        verify(userCartRepo, times(1)).save(any(UserCart.class));
    }


    @Test
    void testReduceUserCartItemQuantity_WhenQuantityGreaterThanOne_DecrementsQuantity()
    {
        long userCartId = 1L;
        long productId = 1L;

        userCart = new UserCart();
        userCart.setId(userCartId);
        userCart.setUserId(10);
        userCart.setCartItems(new ArrayList<>());

        cartItem = new CartItem();
        cartItem.setProductId(productId);
        cartItem.setProductName("Product A");
        cartItem.setDescription("Description");
        cartItem.setProductQuantity(3);
        cartItem.setProductPrice(100);
        cartItem.setCurCartItemPrice(300);

        userCart.getCartItems().add(cartItem);

        when(userCartRepo.findById(userCartId)).thenReturn(Optional.of(userCart));
        when(userCartRepo.save(any(UserCart.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserCartDTO dto = cartService.reduceUserCartItemQuantity(userCartId, productId);

        assertNotNull(dto);
        assertEquals(userCartId, dto.cartId);
        assertEquals(200, dto.totalPrice);

        CartItemDTO cartItemDTO = dto.cartItemDTOS.get(0);
        assertEquals(1, cartItemDTO.productId);
        assertEquals(2, cartItemDTO.productQuantity);

        System.out.println("product quantity -> " + cartItemDTO.productQuantity);
        System.out.println("price -> " + dto.totalPrice);

        verify(userCartRepo, times(1)).findById(userCartId);
        verify(userCartRepo, times(1)).save(any(UserCart.class));
    }

    @Test
    void testReduceUserCartItemQuantity_WhenQuantityIsOne_RemovesItem()
    {
        long userCartId = 1L;
        long productId = 1L;

        userCart = new UserCart();
        userCart.setId(userCartId);
        userCart.setUserId(10L);
        userCart.setCartItems(new ArrayList<>());

        cartItem = new CartItem();
        cartItem.setProductName("Product A");
        cartItem.setProductQuantity(1);
        cartItem.setProductPrice(100);
        cartItem.setProductId(productId);
        cartItem.setCurCartItemPrice(100);

        when(userCartRepo.findById(userCartId)).thenReturn(Optional.of(userCart));
        when(userCartRepo.save(any(UserCart.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserCartDTO dto = cartService.reduceUserCartItemQuantity(userCartId, productId);

        assertEquals(10, dto.userId);
        assertEquals(0, dto.totalPrice);
        assertEquals(0, dto.cartItemDTOS.size());

        System.out.println("Product Qty -> " + dto.cartItemDTOS.size());
        System.out.println("Price -> " + dto.totalPrice);

        verify(userCartRepo, times(1)).findById(userCartId);
        verify(userCartRepo, times(1)).save(any(UserCart.class));
    }

}
