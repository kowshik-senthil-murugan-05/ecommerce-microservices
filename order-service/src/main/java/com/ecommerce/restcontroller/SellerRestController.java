//package com.ecommerce.restcontroller;
//
//import com.ecommerce.app.exceptionhandler.APIResponse;
//import com.ecommerce.app.order.OrderDTO;
//import com.ecommerce.app.order.OrderService;
//import com.ecommerce.app.order.OrderServiceImpl.OrderStatusUpdateDTO;
//import com.ecommerce.app.orderitem.OrderItemDTO;
//import com.ecommerce.app.product.ProductDTO;
//import com.ecommerce.app.product.ProductService;
//import com.ecommerce.app.util.AppUtil;
//import com.ecommerce.app.util.PageDetails;
//import jakarta.validation.Valid;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/seller")
//@PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
//public class SellerRestController {
//
//    private final ProductService productService;
//    private final OrderService orderService;
//
//    public SellerRestController(ProductService productService, OrderService orderService)
//    {
//        this.productService = productService;
//        this.orderService = orderService;
//    }
//
//    // --- Seller Product APIs ---
//    @PostMapping("/product/add")
//    public ResponseEntity<APIResponse<ProductDTO>> addProduct(@Valid @RequestBody ProductDTO dto)
//    {
//        ProductDTO savedProduct = productService.saveProduct(dto);
//
//        return new ResponseEntity<>(
//                new APIResponse<>(
//                        "Product created successfully",
//                        true,
//                        savedProduct),
//                HttpStatus.CREATED
//        );
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity<APIResponse<ProductDTO>> updateProduct(@RequestBody ProductDTO dto)
//    {
//        productService.updateProduct(dto);
//
//        return new ResponseEntity<>(
//                new APIResponse<>(
//                        "Product with product id : " + dto.id + " updated successfully!!",
//                        true, null),
//                HttpStatus.OK
//        );
//    }
//
//    @DeleteMapping("/delete/{productId}")
//    public ResponseEntity<APIResponse<ProductDTO>> deleteProduct(@PathVariable long productId)
//    {
//        productService.deleteProduct(productId);
//
//        return new ResponseEntity<>(
//                new APIResponse<>(
//                        "Product with product id : " + productId + " deleted successfully!!",
//                        true, null),
//                HttpStatus.OK
//        );
//    }
//
//    @GetMapping("/fetch/products/forSeller/{sellerId}")
//    public ResponseEntity<PageDetails<ProductDTO>> fetchProductsBySeller(
//            @PathVariable long sellerId,
//            @RequestParam(value = "pageNumber", defaultValue = AppUtil.DEFAULT_PAGE_NUMBER) int pageNumber,
//            @RequestParam(value = "pageSize", defaultValue = AppUtil.DEFAULT_PAGE_SIZE) int pageSize,
//            @RequestParam(value = "sortBy", defaultValue = AppUtil.SORT_BY) String sortBy,
//            @RequestParam(value = "sortOrder", defaultValue = AppUtil.SORT_DIR) String sortOrder)
//    {
//        PageDetails<ProductDTO> products = productService.fetchProductsBySeller(sellerId, pageNumber, pageSize, sortBy, sortOrder);
//
//        return new ResponseEntity<>(
//                products,
//                HttpStatus.OK
//        );
//    }
//
//    @GetMapping("/fetch/orders/forSeller/{sellerId}")
//    public ResponseEntity<PageDetails<OrderItemDTO>> fetchOrdersForSeller(
//            @PathVariable long sellerId,
//            @RequestParam(value = "pageNumber", defaultValue = AppUtil.DEFAULT_PAGE_NUMBER) int pageNumber,
//            @RequestParam(value = "pageSize", defaultValue = AppUtil.DEFAULT_PAGE_SIZE) int pageSize,
//            @RequestParam(value = "sortBy", defaultValue = AppUtil.SORT_BY) String sortBy,
//            @RequestParam(value = "sortOrder", defaultValue = AppUtil.SORT_DIR) String sortOrder)
//    {
//        PageDetails<OrderItemDTO> pageDetails = orderService.fetchOrdersForSeller(sellerId, pageNumber, pageSize, sortBy, sortOrder);
//
//        return new ResponseEntity<>(
//                pageDetails,
//                HttpStatus.OK
//        );
//    }
//
//    @PutMapping("/update/order/status")
//    public ResponseEntity<APIResponse<OrderDTO>> updateOrderStatus(@RequestBody OrderStatusUpdateDTO statusUpdateDTO)
//    {
//        OrderDTO updateOrderStatus = orderService.updateOrderStatus(statusUpdateDTO);
//
//        return new ResponseEntity<>(
//                new APIResponse<>("Order status updated!", true, updateOrderStatus),
//                HttpStatus.OK
//        );
//    }
//}
//
