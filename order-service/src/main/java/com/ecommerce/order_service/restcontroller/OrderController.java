package com.ecommerce.order_service.restcontroller;

import com.ecommerce.order_service.exceptionhandler.APIResponse;
import com.ecommerce.order_service.order.OrderDTO;
import com.ecommerce.order_service.order.OrderService;
import com.ecommerce.order_service.order.OrderServiceImpl;
import com.ecommerce.order_service.orderitem.OrderItemDTO;
import com.ecommerce.order_service.util.AppUtil;
import com.ecommerce.order_service.util.PageDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public ResponseEntity<APIResponse<OrderDTO>> placeOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO placedOrder = orderService.placeOrder(orderDTO);
        return new ResponseEntity<>(
                new APIResponse<>("Order placed successfully!", true, placedOrder),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/myOrders/fetch/{userId}")
    public ResponseEntity<PageDetails<OrderDTO>> getMyOrders(
            @PathVariable long userId,
            @RequestParam(value = "pageNumber", defaultValue = AppUtil.DEFAULT_PAGE_NUMBER) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppUtil.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppUtil.SORT_BY) String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = AppUtil.SORT_DIR) String sortOrder) {

        PageDetails<OrderDTO> myOrders = orderService.fetchOrdersForUser(userId, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(myOrders, HttpStatus.OK);
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<APIResponse<OrderDTO>> updateOrderStatus(@RequestBody OrderServiceImpl.OrderStatusUpdateDTO statusUpdateDTO) {
        OrderDTO updatedOrder = orderService.updateOrderStatus(statusUpdateDTO);
        return new ResponseEntity<>(
                new APIResponse<>("Order status updated!", true, updatedOrder),
                HttpStatus.OK
        );
    }

    @GetMapping("/fetch/list/{year}/{month}")
    public ResponseEntity<PageDetails<OrderDTO>> listOrdersForMonth(
            @PathVariable int year,
            @PathVariable int month,
            @RequestParam(value = "pageNumber", defaultValue = AppUtil.DEFAULT_PAGE_NUMBER) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppUtil.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppUtil.SORT_BY) String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = AppUtil.SORT_DIR) String sortOrder) {

        PageDetails<OrderDTO> orders = orderService.listOrdersForMonth(year, month, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<APIResponse<OrderDTO>> deleteOrder(@PathVariable long orderId) {
        OrderDTO orderDTO = orderService.deleteOrder(orderId);
        return new ResponseEntity<>(
                new APIResponse<>("Order deleted!", true, orderDTO),
                HttpStatus.OK
        );
    }

    @GetMapping("/list/{sellerId}")
    public ResponseEntity<PageDetails<OrderItemDTO>> fetchOrdersForSeller(
            @PathVariable long sellerId,
            @RequestParam(value = "pageNumber", defaultValue = AppUtil.DEFAULT_PAGE_NUMBER) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppUtil.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppUtil.SORT_BY) String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = AppUtil.SORT_DIR) String sortOrder) {

        PageDetails<OrderItemDTO> pageDetails =
                orderService.fetchOrdersForSeller(sellerId, pageNumber, pageSize, sortBy, sortOrder);

        return new ResponseEntity<>(pageDetails, HttpStatus.OK);
    }

}

