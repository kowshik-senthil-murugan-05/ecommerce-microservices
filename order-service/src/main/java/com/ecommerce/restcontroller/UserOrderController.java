package com.ecommerce.restcontroller;//package com.ecommerce.app.restcontroller;
//
//import com.ecommerce.app.exceptionhandler.APIResponse;
//import com.ecommerce.app.order.OrderDTO;
//import com.ecommerce.app.order.OrderService;
//import com.ecommerce.app.util.AppUtil;
//import com.ecommerce.app.util.PageDetails;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/user/order")
//public class UserOrderController
//{
//
//    @Autowired
//    private OrderService orderService;
//
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
//    @GetMapping("/get/forUser/{userId}")
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
//}
