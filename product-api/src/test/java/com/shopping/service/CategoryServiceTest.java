package com.shopping.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.shopping.dto.CategoryRequestDTO;
import com.shopping.dto.CategoryResponseDTO;
import com.shopping.entity.Category;
import com.shopping.exception.CustomException;
import com.shopping.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CategoryServiceTest {

  @Mock
  private CategoryRepository categoryRepository;

  @InjectMocks
  private CategoryService categoryService;

  public CategoryServiceTest() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void createCategory_success() {
    Category category = Category.builder().id(1L).name("Electronics").build();
    when(categoryRepository.save(any(Category.class))).thenReturn(category);

    CategoryRequestDTO categoryRequestDTO = CategoryRequestDTO.builder().name("Electronics").build();
    CategoryResponseDTO createdCategory = categoryService.createCategory(categoryRequestDTO);

    verify(categoryRepository, times(1)).save(any(Category.class));
  }

  @Test
  public void updateCategory_categoryNotFound() {
    when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());

    CategoryRequestDTO categoryRequestDTO = CategoryRequestDTO.builder().name("Electronics").build();
    assertThrows(CustomException.class, () -> {
      categoryService.updateCategory(1L, categoryRequestDTO);
    });

    verify(categoryRepository, times(1)).findById(anyLong());
  }

  @Test
  public void deleteCategory_categoryNotFound() {
    when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());

    assertThrows(CustomException.class, () -> {
      categoryService.deleteCategory(1L);
    });

    verify(categoryRepository, times(1)).findById(anyLong());
  }

  @Test
  public void getCategoryById_categoryNotFound() {
    when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());

    assertThrows(CustomException.class, () -> {
      categoryService.getCategoryById(1L);
    });

    verify(categoryRepository, times(1)).findById(anyLong());
  }

  @Test
  public void getAllCategories_success() {
    when(categoryRepository.findAll()).thenReturn(List.of(
        Category.builder().id(1L).name("Electronics").build(),
        Category.builder().id(2L).name("Books").build()
    ));

    List<CategoryResponseDTO> categories = categoryService.getAllCategories();

    verify(categoryRepository, times(1)).findAll();
  }
}
