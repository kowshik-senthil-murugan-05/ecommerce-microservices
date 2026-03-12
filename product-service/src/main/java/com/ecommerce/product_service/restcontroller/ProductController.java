package com.ecommerce.product_service.restcontroller;

import com.ecommerce.product_service.category.CategoryDTO;
import com.ecommerce.product_service.category.CategoryService;
import com.ecommerce.product_service.exceptionhandler.APIResponse;
import com.ecommerce.product_service.product.ProductDTO;
import com.ecommerce.product_service.product.ProductService;
import com.ecommerce.product_service.util.AppUtil;
import com.ecommerce.product_service.util.PageDetails;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final CategoryService categoryService;
    private final ProductService productService;

    public ProductController(CategoryService categoryService, ProductService productService)
    {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @PostMapping("/category/add")
    public ResponseEntity<APIResponse<CategoryDTO>> addCategory(@Valid @RequestBody CategoryDTO dto)
    {
        CategoryDTO categoryDTO = categoryService.addCategory(dto);

        return new ResponseEntity<>(
                new APIResponse<>(
                        "Category created successfully!!",
                        true,
                        categoryDTO),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/categories/listAll")
    public ResponseEntity<PageDetails<CategoryDTO>> listAllCategories
            (@RequestParam(name = "pageNum", defaultValue = AppUtil.DEFAULT_PAGE_NUMBER) int pageNum,
             @RequestParam(value = "pageSize", defaultValue = AppUtil.DEFAULT_PAGE_SIZE) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppUtil.SORT_BY) String sortBy,
             @RequestParam(value = "sortOrder", defaultValue = AppUtil.SORT_DIR) String sortOrder)
    {
        PageDetails<CategoryDTO> categoryDTOPageDetails = categoryService.listAllCategories(pageNum, pageSize, sortBy, sortOrder);

        return new ResponseEntity<>(
                categoryDTOPageDetails,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/category/delete/{categoryId}")
    public ResponseEntity<APIResponse<Void>> removeCategory(@PathVariable long categoryId)
    {
        categoryService.removeCategory(categoryId);

        return new ResponseEntity<>(
                new APIResponse<>(
                        "Category with category id : " + categoryId + " removed successfully!!",
                        true, null
                ),
                HttpStatus.OK
        );
    }

    @PutMapping("/category/update")
    public ResponseEntity<APIResponse<CategoryDTO>> updateCategory(@RequestBody CategoryDTO dto)
    {
        CategoryDTO updatedCategory = categoryService.updateCategory(dto);

        return new ResponseEntity<>(
                new APIResponse<>(
                        "Category updated!",
                        true,
                        updatedCategory),
                HttpStatus.OK
        );
    }

    //todo Get category by ID

    @PostMapping("/add")
    public ResponseEntity<APIResponse<ProductDTO>> addProduct(@Valid @RequestBody ProductDTO dto)
    {
        ProductDTO savedProduct = productService.saveProduct(dto);

        return new ResponseEntity<>(
                new APIResponse<>(
                        "Product created successfully",
                        true,
                        savedProduct),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update")
    public ResponseEntity<APIResponse<ProductDTO>> updateProduct(@RequestBody ProductDTO dto)
    {
        productService.updateProduct(dto);

        return new ResponseEntity<>(
                new APIResponse<>(
                        "Product with product id : " + dto.id + " updated successfully!!",
                        true, null),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<APIResponse<ProductDTO>> deleteProduct(@PathVariable long productId)
    {
        productService.deleteProduct(productId);

        return new ResponseEntity<>(
                new APIResponse<>(
                        "Product with product id : " + productId + " deleted successfully!!",
                        true, null),
                HttpStatus.OK
        );
    }

    @GetMapping("/fetch/forSeller/{sellerId}")
    public ResponseEntity<PageDetails<ProductDTO>> fetchProductsBySeller(
            @PathVariable long sellerId,
            @RequestParam(value = "pageNumber", defaultValue = AppUtil.DEFAULT_PAGE_NUMBER) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppUtil.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppUtil.SORT_BY) String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = AppUtil.SORT_DIR) String sortOrder)
    {
        PageDetails<ProductDTO> products = productService.fetchProductsBySeller(sellerId, pageNumber, pageSize, sortBy, sortOrder);

        return new ResponseEntity<>(
                products,
                HttpStatus.OK
        );
    }

    @GetMapping("/listAll")
    public ResponseEntity<PageDetails<ProductDTO>> listAllProducts(
            @RequestParam(value = "pageNumber", defaultValue = AppUtil.DEFAULT_PAGE_NUMBER) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppUtil.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppUtil.SORT_BY) String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = AppUtil.SORT_DIR) String sortOrder)
    {
        PageDetails<ProductDTO> allProducts = productService.getAllProducts(pageNumber, pageSize, sortBy, sortOrder);

        return new ResponseEntity<>(
                allProducts,
                HttpStatus.OK
        );
    }

    @GetMapping("/listAll/byCategory/{categoryId}")
    public ResponseEntity<PageDetails<ProductDTO>> listAllProductsForCategory(
            @RequestParam(value = "pageNumber", defaultValue = AppUtil.DEFAULT_PAGE_NUMBER) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppUtil.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppUtil.SORT_BY) String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = AppUtil.SORT_DIR) String sortOrder,
            @PathVariable long categoryId)
    {
        PageDetails<ProductDTO> allProductsForCategoryId = productService.getAllProductsForCategoryId(pageNumber, pageSize, sortBy, sortOrder, categoryId);

        return new ResponseEntity<>(
                allProductsForCategoryId,
                HttpStatus.OK
        );
    }

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

