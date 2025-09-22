//package com.ecommerce.restcontroller;
//
//import com.ecommerce.app.appuser.address.UserAddressDTO;
//import com.ecommerce.app.appuser.address.UserAddressService;
//import com.ecommerce.app.cart.AddToCartRequestDTO;
//import com.ecommerce.app.cart.CartService;
//import com.ecommerce.app.cart.UserCartDTO;
//import com.ecommerce.app.exceptionhandler.APIResponse;
//import com.ecommerce.app.order.OrderDTO;
//import com.ecommerce.app.order.OrderService;
//import com.ecommerce.app.util.AppUtil;
//import com.ecommerce.app.util.PageDetails;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/user")
//@PreAuthorize("hasRole('USER')")
//public class UserRestController
//{
//    private final CartService cartService;
//    private final UserAddressService userAddressService;
//    private final OrderService orderService;
//
//    public UserRestController(CartService cartService, UserAddressService userAddressService,
//                              OrderService orderService)
//    {
//        this.cartService = cartService;
//        this.userAddressService = userAddressService;
//        this.orderService = orderService;
//    }
//
//    //Cart
//    @PostMapping("/product/addToCart/{userId}")
//    public ResponseEntity<APIResponse<UserCartDTO>> addProductToCart(
//            @PathVariable long userId, @RequestBody AddToCartRequestDTO requestDTO)
//    {
//        /*
//         *   Adding products to cart is tested. It is working fine.
//         *   todo - Adding same product again results in creation of two rows, it should be added in same row.
//         */
////        return cartService.addProductToCart(productId, requiredProductQuantity, userId);
//        UserCartDTO dto = cartService.addProductToCart(userId, requestDTO);
//
//        return new ResponseEntity<>(
//                new APIResponse<>(
//                        "Product added to cart!!",
//                        true,
//                        dto),
//                HttpStatus.CREATED
//        );
//    }//
//
//    @GetMapping("/userCart/get/{userId}")
//    public ResponseEntity<UserCartDTO> getCartForUserId(@PathVariable long userId){
//        UserCartDTO cartForUserId = cartService.getUserCartForUserId(userId);
//
//        return new ResponseEntity<>(
//                cartForUserId,
//                HttpStatus.OK
//        );
//    }
//
//    @PostMapping("/cartItem/remove/{userCartId}/{productId}")
//    public ResponseEntity<APIResponse<Void>> removeCartItem(@PathVariable long userCartId, @PathVariable long productId)
//    {
//        cartService.removeCartItem(userCartId, productId);
//
//        return new ResponseEntity<>(
//                new APIResponse<>(
//                        "Cart item removed successfully!!",
//                        true, null),
//                HttpStatus.OK
//        );
//    }
//
//    @PostMapping("/cartItem/quantity/reduce/{userCartId}/{productId}")
//    public ResponseEntity<UserCartDTO> reduceCartItemQuantity(@PathVariable long userCartId, @PathVariable long productId)
//    {
//        UserCartDTO reducedUserCartItemQuantity = cartService.reduceUserCartItemQuantity(userCartId, productId);
//
//        return new ResponseEntity<UserCartDTO>(
//                reducedUserCartItemQuantity,
//                HttpStatus.OK
//        );
//    }
//
//    //Address
//    @PostMapping("/address/add/{userId}")
//    public ResponseEntity<APIResponse<UserAddressDTO>> addAddress(@PathVariable long userId, @RequestBody UserAddressDTO addressDTO)
//    {
//        UserAddressDTO addedAddress = userAddressService.addAddress(userId, addressDTO);
//
//        return new ResponseEntity<>(
//                new APIResponse<>("Address for user added!", true, addedAddress),
//                HttpStatus.OK
//        );
//    }
//
//    @PutMapping("/address/update/{userId}")
//    public ResponseEntity<APIResponse<UserAddressDTO>> updateAddress(@PathVariable long userId, @RequestBody UserAddressDTO addressDTO)
//    {
//        UserAddressDTO updatedAddress = userAddressService.updateAddress(userId, addressDTO);
//
//        return new ResponseEntity<>(
//                new APIResponse<>("User address updated!", true, updatedAddress),
//                HttpStatus.OK
//        );
//    }
//
//    @DeleteMapping("/address/delete/{userId}/{addressId}")
//    public ResponseEntity<APIResponse<Void>> deleteAddress(@PathVariable long userId, @PathVariable long addressId)
//    {
//        userAddressService.deleteAddress(userId, addressId);
//
//        return new ResponseEntity<>(
//                new APIResponse<>("User address deleted!", true, null),
//                HttpStatus.OK
//        );
//    }
//
//    @GetMapping("/fetch/specificAddress/forUser/{userId}/{addressId}")
//    public ResponseEntity<UserAddressDTO> fetchSpecificAddressForUser(@PathVariable long userId, @PathVariable long addressId) {
//        UserAddressDTO specificAddressForUser = userAddressService.fetchSpecificAddressForUser(userId, addressId);
//
//        return new ResponseEntity<>(
//                specificAddressForUser,
//                HttpStatus.OK
//        );
//    }
//
//    @GetMapping("/fetch/allAddresses/forUser/{userId}")
//    public ResponseEntity<PageDetails<UserAddressDTO>> fetchAllAddressesForUser(
//            @PathVariable long userId,
//            @RequestParam(name = "pageNum", defaultValue = AppUtil.DEFAULT_PAGE_NUMBER) int pageNum,
//            @RequestParam(value = "pageSize", defaultValue = AppUtil.DEFAULT_PAGE_SIZE) int pageSize,
//            @RequestParam(value = "sortBy", defaultValue = AppUtil.SORT_BY) String sortBy,
//            @RequestParam(value = "sortOrder", defaultValue = AppUtil.SORT_DIR) String sortOrder)
//    {
//        PageDetails<UserAddressDTO> fetchedAllAddressesForUser = userAddressService.fetchAllAddressesForUser(userId, pageNum, pageSize, sortBy, sortOrder);
//
//        return new ResponseEntity<>(
//                fetchedAllAddressesForUser,
//                HttpStatus.OK
//        );
//    }
//
//    //Order
//    @PostMapping("/place")
//    public ResponseEntity<APIResponse<OrderDTO>> placeOrder(@RequestBody OrderDTO dto) {
//        OrderDTO orderDTO = orderService.placeOrder(dto);
//
//        return new ResponseEntity<>(
//                new APIResponse<>(
//                        "Order placed successfully!!",
//                        true,
//                        orderDTO
//                ),
//                HttpStatus.OK
//        );
//    }
//
//    @GetMapping("/fetch/forUser/{userId}")
//    public ResponseEntity<PageDetails<OrderDTO>> fetchOrdersForUser(
//            @PathVariable long userId,
//            @RequestParam(value = "pageNumber", defaultValue = AppUtil.DEFAULT_PAGE_NUMBER) int pageNumber,
//            @RequestParam(value = "pageSize", defaultValue = AppUtil.DEFAULT_PAGE_SIZE) int pageSize,
//            @RequestParam(value = "sortBy", defaultValue = AppUtil.SORT_BY) String sortBy,
//            @RequestParam(value = "sortOrder", defaultValue = AppUtil.SORT_DIR) String sortOrder)
//    {
//        PageDetails<OrderDTO> pageDetails = orderService.fetchOrdersForUser(userId, pageNumber, pageSize, sortBy, sortOrder);
//
//        return new ResponseEntity<>(
//                pageDetails,
//                HttpStatus.OK
//        );
//    }
//
//    @GetMapping("/orderData/fetch/{orderId}")
//    public ResponseEntity<APIResponse<OrderDTO>> fetchOrderData(@PathVariable long orderId)
//    {
//        OrderDTO orderData = orderService.getOrderData(orderId);
//
//        return new ResponseEntity<>(
//                new APIResponse<>("Order data fetched!", true, orderData),
//                HttpStatus.OK
//        );
//    }
//
//    @PutMapping("/order/cancel/{orderId}")
//    public ResponseEntity<APIResponse<OrderDTO>> cancelOrder(@PathVariable long orderId)
//    {
//        OrderDTO updateOrderStatus = orderService.cancelOrder(orderId);
//
//        return new ResponseEntity<>(
//                new APIResponse<>("Order status updated!", true, updateOrderStatus),
//                HttpStatus.OK
//        );
//    }
//
//}
