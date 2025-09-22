//package com.ecommerce.restcontroller;
//
//import com.ecommerce.app.product.ProductDTO;
//import com.ecommerce.app.product.ProductService;
//import com.ecommerce.app.util.AppUtil;
//import com.ecommerce.app.util.PageDetails;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/user/product")
//public class UserProductController
//{
//
//    @Autowired
//    private ProductService productService;
//
//    @GetMapping("/search")
//    public ResponseEntity<PageDetails<ProductDTO>> searchProducts(
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String category,
//            @RequestParam(value = "pageNumber", defaultValue = AppUtil.DEFAULT_PAGE_NUMBER) int pageNumber,
//            @RequestParam(value = "pageSize", defaultValue = AppUtil.DEFAULT_PAGE_SIZE) int pageSize,
//            @RequestParam(value = "sortOrder", defaultValue = AppUtil.SORT_DIR) String sortOrder,
//            @RequestParam(value = "sortBy", defaultValue = AppUtil.SORT_BY) String sortBy
//            )
//    {
//        PageDetails<ProductDTO> productDTOS = productService.searchProducts(name, category, pageNumber, pageSize, sortOrder, sortBy);
//
//        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
//    }
//
//    @GetMapping("/listAll")
//    public ResponseEntity<PageDetails<ProductDTO>> listAllProducts(
//            @RequestParam(value = "pageNumber", defaultValue = AppUtil.DEFAULT_PAGE_NUMBER) int pageNumber,
//            @RequestParam(value = "pageSize", defaultValue = AppUtil.DEFAULT_PAGE_SIZE) int pageSize,
//            @RequestParam(value = "sortBy", defaultValue = AppUtil.SORT_BY) String sortBy,
//            @RequestParam(value = "sortOrder", defaultValue = AppUtil.SORT_DIR) String sortOrder)
//    {
//        PageDetails<ProductDTO> allProducts = productService.getAllProducts(pageNumber, pageSize, sortBy, sortOrder);
//
//        return new ResponseEntity<>(
//                allProducts,
//                HttpStatus.OK
//        );
//    }
//
//    @GetMapping("/listAll/byCategory/{categoryId}")
//    public ResponseEntity<PageDetails<ProductDTO>> listAllProductsForCategory(
//            @RequestParam(value = "pageNumber", defaultValue = AppUtil.DEFAULT_PAGE_NUMBER) int pageNumber,
//            @RequestParam(value = "pageSize", defaultValue = AppUtil.DEFAULT_PAGE_SIZE) int pageSize,
//            @RequestParam(value = "sortBy", defaultValue = AppUtil.SORT_BY) String sortBy,
//            @RequestParam(value = "sortOrder", defaultValue = AppUtil.SORT_DIR) String sortOrder,
//            @PathVariable long categoryId)
//    {
//        PageDetails<ProductDTO> allProductsForCategoryId = productService.getAllProductsForCategoryId(pageNumber, pageSize, sortBy, sortOrder, categoryId);
//
//        return new ResponseEntity<>(
//                allProductsForCategoryId,
//                HttpStatus.OK
//        );
//    }
//}
