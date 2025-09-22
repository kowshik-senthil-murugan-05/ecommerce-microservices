package com.ecommerce.restcontroller;

import com.ecommerce.exceptionhandler.APIResponse;
import com.ecommerce.order.OrderDTO;
import com.ecommerce.order.OrderServiceImpl;
import com.ecommerce.order.OrderServiceImpl.OrderStatusUpdateDTO;
import com.ecommerce.orderitem.OrderItemDTO;
import com.ecommerce.order.OrderService;
import com.ecommerce.util.AppUtil;
import com.ecommerce.util.PageDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seller/orders")
@PreAuthorize("hasRole('SELLER')")
public class OrderSellerController {

    private final OrderService orderService;

    public OrderSellerController(OrderService orderService) {
        this.orderService = orderService;
    }

    // ✅ Fetch all order items for a seller
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

    // Seller can update item status (ex: mark as shipped)
    @PutMapping("/update-status")
    public ResponseEntity<APIResponse<OrderDTO>> updateOrderItemStatus(
            @RequestBody OrderStatusUpdateDTO orderStatusUpdateDTO) {

        OrderDTO updatedItem = orderService.updateOrderStatus(orderStatusUpdateDTO);

        return new ResponseEntity<>(
                new APIResponse<>("Order item status updated!", true, updatedItem),
                HttpStatus.OK
        );
    }

}

