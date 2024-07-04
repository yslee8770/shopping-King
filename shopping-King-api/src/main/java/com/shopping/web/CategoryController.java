package com.shopping.web;

import com.shopping.dto.CategoryDto;
import com.shopping.mapper.CategoryMapper;
import com.shopping.service.CategoryService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  public ResponseEntity<List<CategoryDto>> findCategoryList(
      @RequestParam(required = false) String categoryName,
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "asc") String sort) {
    return ResponseEntity
        .ok(categoryService
            .findCategoryList(categoryName, page, size, sort)
            .stream()
            .map(CategoryMapper::categoryToCategoryDto)
            .collect(Collectors.toList()));
  }

  @PostMapping
  public ResponseEntity<CategoryDto> createCategory(
      @RequestBody CategoryDto categoryCreateDto) {
    return ResponseEntity.ok(CategoryMapper.categoryToCategoryDto(categoryService.createCategory(categoryCreateDto)));
  }

  @GetMapping("/{categoryId}")
  public ResponseEntity<CategoryDto> findCategoryDetail(@PathVariable Long categoryId) {
    return ResponseEntity
        .ok(CategoryMapper
            .categoryToCategoryDto(categoryService.findCategoryByCategoryId(categoryId)));
  }

  @PatchMapping("/{categoryId}")
  public ResponseEntity<CategoryDto> changeCategory(@PathVariable Long categoryId,
      @RequestBody CategoryDto categoryRequestDto) {
    return ResponseEntity
        .ok(CategoryMapper
            .categoryToCategoryDto(categoryService.updateCategory(categoryRequestDto)));
  }

  @DeleteMapping("/{categoryId}")
  public ResponseEntity<Long> deleteCategory(@PathVariable Long categoryId) {
    categoryService.deleteCategory(categoryId);
    return ResponseEntity.ok(categoryId);
  }
}
