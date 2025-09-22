package com.ecommerce.app.categorytest;

import com.ecommerce.app.category.Category;
import com.ecommerce.app.category.CategoryDTO;
import com.ecommerce.app.category.CategoryRepo;
import com.ecommerce.app.category.CategoryServiceImpl;
import com.ecommerce.app.exceptionhandler.APIException;
import com.ecommerce.app.exceptionhandler.ResourceNotFoundException;
import com.ecommerce.app.util.PageDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest
{
    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepo categoryRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCategory_Success() {
        CategoryDTO dto = new CategoryDTO(0, "Electronics");
        when(categoryRepo.findByCategoryNameIgnoreCase("Electronics")).thenReturn(null);
        when(categoryRepo.save(any(Category.class))).thenReturn(new Category(1, "Electronics"));

        CategoryDTO result = categoryService.addCategory(dto);

        System.out.println("output -> " + "id -> " + result.id + " name -> " + result.categoryName);

        assertEquals("Electronics", result.categoryName);
        verify(categoryRepo, times(1)).save(any(Category.class));
    }

    @Test
    void testAddCategory_DuplicateName_ThrowsException() {
        CategoryDTO dto = new CategoryDTO(0, "Electronics");
        when(categoryRepo.findByCategoryNameIgnoreCase("Electronics")).thenReturn(new Category(1, "Electronics"));

        assertThrows(APIException.class, () -> categoryService.addCategory(dto));
    }

    @Test
    void testListAllCategories_Success() {
        List<Category> categories = Arrays.asList(new Category(1, "Electronics"), new Category(2, "Books"));
        Page<Category> page = new PageImpl<>(categories);
        when(categoryRepo.findAll(any(Pageable.class))).thenReturn(page);

        PageDetails<CategoryDTO> result = categoryService.listAllCategories(0, 5, "id", "asc");

        assertEquals(2, result.pageSize);
    }

    @Test
    void testListAllCategories_NoCategories_ThrowsException() {
        Page<Category> emptyPage = new PageImpl<>(List.of());
        when(categoryRepo.findAll(any(Pageable.class))).thenReturn(emptyPage);

        assertThrows(APIException.class, () -> categoryService.listAllCategories(0, 5, "id", "asc"));
    }

    @Test
    void testRemoveCategory_Success() {
        when(categoryRepo.findById(1L)).thenReturn(Optional.of(new Category(1, "Electronics")));

        assertDoesNotThrow(() -> categoryService.removeCategory(1L));
        verify(categoryRepo, times(1)).delete(any(Category.class));
    }

    @Test
    void testRemoveCategory_NotFound_ThrowsException() {
        when(categoryRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> categoryService.removeCategory(1L));
    }

    @Test
    void testUpdateCategory_Success() {
        CategoryDTO dto = new CategoryDTO(1, "UpdatedElectronics");
        Category existingCategory = new Category(1, "OldElectronics");

        when(categoryRepo.findById(1L)).thenReturn(Optional.of(existingCategory));
        when(categoryRepo.findByCategoryNameIgnoreCase("UpdatedElectronics")).thenReturn(null);
        when(categoryRepo.save(any(Category.class))).thenReturn(new Category(1, "UpdatedElectronics"));

        CategoryDTO result = categoryService.updateCategory(dto);

        assertEquals("UpdatedElectronics", result.categoryName);
    }

    @Test
    void testUpdateCategory_DuplicateName_ThrowsException() {
        CategoryDTO dto = new CategoryDTO(1, "Books");
        Category existingCategory = new Category(1, "Electronics");
        Category duplicateCategory = new Category(2, "Books");

        when(categoryRepo.findById(1L)).thenReturn(Optional.of(existingCategory));
        when(categoryRepo.findByCategoryNameIgnoreCase("Books")).thenReturn(duplicateCategory);

        assertThrows(APIException.class, () -> categoryService.updateCategory(dto));
    }

    @Test
    void testUpdateCategory_NotFound_ThrowsException() {
        CategoryDTO dto = new CategoryDTO(1, "Books");

        when(categoryRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> categoryService.updateCategory(dto));
    }

    @Test
    void testIsValidCategory_ReturnsTrue() {
        when(categoryRepo.existsById(1L)).thenReturn(true);

        assertTrue(categoryService.isValidCategory(1L));
    }

    @Test
    void testIsValidCategory_ReturnsFalse() {
        when(categoryRepo.existsById(1L)).thenReturn(false);

        assertFalse(categoryService.isValidCategory(1L));
    }


}

