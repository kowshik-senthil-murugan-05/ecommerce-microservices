package com.ecommerce.restcontroller;

import com.ecommerce.exceptionhandler.APIResponse;
import com.ecommerce.order.OrderDTO;
import com.ecommerce.order.OrderService;
import com.ecommerce.util.AppUtil;
import com.ecommerce.util.PageDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/orders")
@PreAuthorize("hasRole('USER', 'ADMIN')")
public class OrderUserController {

    private final OrderService orderService;

    public OrderUserController(OrderService orderService) {
        this.orderService = orderService;
    }

    // ✅ Place order
    @PostMapping("/place")
    public ResponseEntity<APIResponse<OrderDTO>> placeOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO placedOrder = orderService.placeOrder(orderDTO);
        return new ResponseEntity<>(
                new APIResponse<>("Order placed successfully!", true, placedOrder),
                HttpStatus.CREATED
        );
    }

    //Get all orders of logged-in user
    @GetMapping("/myOrders/{userId}")
    public ResponseEntity<PageDetails<OrderDTO>> getMyOrders(
            @PathVariable long userId,
            @RequestParam(value = "pageNumber", defaultValue = AppUtil.DEFAULT_PAGE_NUMBER) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppUtil.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppUtil.SORT_BY) String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = AppUtil.SORT_DIR) String sortOrder) {

        PageDetails<OrderDTO> myOrders = orderService.fetchOrdersForUser(userId, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(myOrders, HttpStatus.OK);
    }
}

