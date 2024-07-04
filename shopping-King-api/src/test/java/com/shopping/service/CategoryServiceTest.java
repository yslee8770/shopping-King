package com.shopping.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.shopping.dto.CategoryDto;
import com.shopping.entity.Category;
import com.shopping.enums.DeleteAt;
import com.shopping.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class CategoryServiceTest {

  @Mock
  private CategoryRepository categoryRepository;

  @InjectMocks
  private CategoryService categoryService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("카테고리 이름이 포함된 목록 조회 테스트")
  public void testFindCategoryList_WithName() {
    Category category = Category.builder().Id(1L).name("Test Category").deleteAt(DeleteAt.N)
        .build();
    Page<Category> page = new PageImpl<>(Collections.singletonList(category));
    when(
        categoryRepository.findByNameContaining(any(String.class), any(Pageable.class))).thenReturn(
        page);

    List<Category> categories = categoryService.findCategoryList("Test", 0, 10, "asc");
    assertThat(categories).hasSize(1);
    assertThat(categories.get(0).getName()).isEqualTo("Test Category");
  }

  @Test
  @DisplayName("카테고리 이름 없이 전체 목록 조회 테스트")
  public void testFindCategoryList_WithoutName() {
    Category category = Category.builder().Id(1L).name("Test Category").deleteAt(DeleteAt.N)
        .build();
    Page<Category> page = new PageImpl<>(Collections.singletonList(category));
    when(categoryRepository.findAll(any(Pageable.class))).thenReturn(page);

    List<Category> categories = categoryService.findCategoryList(null, 0, 10, "asc");
    assertThat(categories).hasSize(1);
    assertThat(categories.get(0).getName()).isEqualTo("Test Category");
  }

  @Test
  @DisplayName("카테고리 ID로 카테고리 조회 테스트")
  public void testFindCategoryByCategoryId() {
    Category category = Category.builder().Id(1L).name("Test Category").deleteAt(DeleteAt.N)
        .build();
    when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

    Category foundCategory = categoryService.findCategoryByCategoryId(1L);
    assertThat(foundCategory).isNotNull();
    assertThat(foundCategory.getName()).isEqualTo("Test Category");
  }

  @Test
  @DisplayName("존재하지 않는 카테고리 ID로 조회 시 예외 발생 테스트")
  public void testFindCategoryByCategoryId_NotFound() {
    when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

    Exception exception = assertThrows(EntityNotFoundException.class, () -> {
      categoryService.findCategoryByCategoryId(1L);
    });

    assertThat(exception.getMessage()).isEqualTo("Category not found with id: 1");
  }

  @Test
  @DisplayName("카테고리 생성 서비스 테스트")
  public void testCreateCategory() {
    CategoryDto categoryCreateDto = CategoryDto.builder()
        .name("New Category")
        .build();

    Category category = Category.builder()
        .Id(1L)
        .name("New Category")
        .build();

    when(categoryRepository.save(any(Category.class))).thenReturn(category);

    Category createdCategory = categoryService.createCategory(categoryCreateDto);

    assertEquals("New Category", createdCategory.getName());
    verify(categoryRepository, times(1)).save(any(Category.class));
  }

  @Test
  @DisplayName("카테고리 업데이트 서비스 테스트")
  public void testUpdateCategory() {
    CategoryDto categoryDto = CategoryDto.builder()
        .categoryId(1L)
        .name("Updated Category")
        .deleteAt(DeleteAt.N)
        .build();

    Category category = Category.builder()
        .Id(1L)
        .name("Original Category")
        .deleteAt(DeleteAt.N)
        .build();

    when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

    Category updatedCategory = categoryService.updateCategory(categoryDto);

    assertEquals("Updated Category", updatedCategory.getName());
    verify(categoryRepository, times(1)).findById(1L);
  }

  @Test
  @DisplayName("카테고리 업데이트 실패 서비스 테스트 - 카테고리 없음")
  public void testUpdateCategory_NotFound() {
    CategoryDto categoryDto = CategoryDto.builder()
        .categoryId(1L)
        .name("Updated Category")
        .build();

    when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> categoryService.updateCategory(categoryDto));
    verify(categoryRepository, times(1)).findById(1L);
  }

  @Test
  @DisplayName("카테고리 삭제 서비스 테스트")
  public void testDeleteCategory() {
    Category category = Category.builder()
        .Id(1L)
        .name("Category")
        .deleteAt(DeleteAt.N)
        .build();

    when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

    categoryService.deleteCategory(1L);

    assertEquals(DeleteAt.Y, category.getDeleteAt());
    verify(categoryRepository, times(1)).findById(1L);
  }


  @Test
  @DisplayName("카테고리 삭제 실패 서비스 테스트 - 카테고리 없음")
  public void testDeleteCategory_NotFound() {
    when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> categoryService.deleteCategory(1L));
    verify(categoryRepository, times(1)).findById(1L);
  }
}
