package com.shop.category.web;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shop.category.dto.CategoryDto;
import com.shop.category.service.CategoryService;
import com.shop.common.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping("/list")
  public ResponseEntity<List<CategoryDto>> findCategoryList() {
    return ResponseEntity
        .ok(categoryService
            .findCategoryList()
            .stream()
            .map(CategoryMapper::categoryToCategoryeDto)
            .collect(Collectors.toList()));
  }

  @GetMapping("/detail/{categoryId}")
  public ResponseEntity<CategoryDto> findCategoryDetail(@PathVariable Long categoryId) {
    return ResponseEntity
        .ok(CategoryMapper
            .categoryToCategoryeDto(categoryService.findCategoryByCategoryId(categoryId)));
  }

  @PutMapping("/change")
  public ResponseEntity<CategoryDto> changeCategory(@RequestBody CategoryDto categoryRequestDto) {
    return ResponseEntity
        .ok(CategoryMapper
            .categoryToCategoryeDto(categoryService.saveCategory(categoryRequestDto)));
  }
  // @DeleteMapping("/delete/{categoryId}")
  // public ResponseEntity<Long> deleteCategory(@PathVariable Long categoryId) {
  // return ResponseEntity.ok(categoryId);
  // }
}
