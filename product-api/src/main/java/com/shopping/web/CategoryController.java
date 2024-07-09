package com.shopping.web;

import com.shopping.dto.CategoryRequestDTO;
import com.shopping.dto.CategoryResponseDTO;
import com.shopping.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @PostMapping
  public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
    return ResponseEntity.ok(categoryService.createCategory(categoryRequestDTO));
  }

  @PutMapping("/{categoryId}")
  public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryRequestDTO categoryRequestDTO) {
    return ResponseEntity.ok(categoryService.updateCategory(categoryId, categoryRequestDTO));
  }

  @DeleteMapping("/{categoryId}")
  public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
    categoryService.deleteCategory(categoryId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{categoryId}")
  public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long categoryId) {
    return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
  }

  @GetMapping
  public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
    return ResponseEntity.ok(categoryService.getAllCategories());
  }
}
