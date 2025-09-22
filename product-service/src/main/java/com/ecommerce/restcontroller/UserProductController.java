package com.ecommerce.restcontroller;

import com.ecommerce.product.ProductDTO;
import com.ecommerce.product.ProductService;
import com.ecommerce.util.PageDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@PreAuthorize("hasRole('USER', 'ADMIN')")
public class UserProductController
{
    private final ProductService productService;
    public UserProductController(ProductService productService) { this.productService = productService; }

    @GetMapping("/fetch/all")
    public ResponseEntity<PageDetails<ProductDTO>> fetchAllProducts(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {
        return ResponseEntity.ok(productService.getAllProducts(pageNumber, pageSize, sortBy, sortOrder));
    }

    @GetMapping("/fetch/{productId}")
    public ResponseEntity<ProductDTO> fetchProduct(@PathVariable long productId) {
        return ResponseEntity.ok(productService.getProductObjForProductId(productId));
    }

    @GetMapping("/search")
    public ResponseEntity<PageDetails<ProductDTO>> searchProduct(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {
        return ResponseEntity.ok(productService.searchProducts(name, category, pageNumber, pageSize, sortOrder, sortBy));
    }
}
