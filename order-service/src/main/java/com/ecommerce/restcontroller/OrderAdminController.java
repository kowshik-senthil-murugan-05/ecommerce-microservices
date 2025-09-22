package com.ecommerce.restcontroller;

import com.ecommerce.exceptionhandler.APIResponse;
import com.ecommerce.order.OrderDTO;
import com.ecommerce.order.OrderService;
import com.ecommerce.order.OrderServiceImpl;
import com.ecommerce.order.OrderServiceImpl.OrderStatusUpdateDTO;
import com.ecommerce.util.AppUtil;
import com.ecommerce.util.PageDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/orders")
@PreAuthorize("hasRole('ADMIN')")
public class OrderAdminController {

    private final OrderService orderService;

    public OrderAdminController(OrderService orderService) {
        this.orderService = orderService;
    }

    // ✅ Update order status
    @PutMapping("/update-status")
    public ResponseEntity<APIResponse<OrderDTO>> updateOrderStatus(@RequestBody OrderStatusUpdateDTO statusUpdateDTO) {
        OrderDTO updatedOrder = orderService.updateOrderStatus(statusUpdateDTO);
        return new ResponseEntity<>(
                new APIResponse<>("Order status updated!", true, updatedOrder),
                HttpStatus.OK
        );
    }

    // ✅ List orders by month
    @GetMapping("/list/{year}/{month}")
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

    // ✅ Delete order
    @DeleteMapping("/{orderId}")
    public ResponseEntity<APIResponse<OrderDTO>> deleteOrder(@PathVariable long orderId) {
        OrderDTO orderDTO = orderService.deleteOrder(orderId);
        return new ResponseEntity<>(
                new APIResponse<>("Order deleted!", true, orderDTO),
                HttpStatus.OK
        );
    }
}
