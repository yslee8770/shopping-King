package com.shopping.category.web;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.shopping.category.dto.CategoryDto;
import com.shopping.category.service.CategoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping("/list")
  public ResponseEntity<List<CategoryDto>> findCategoryList() {
    // List<CategoryDto> categories = new ArrayList<CategoryDto>();
    // categories
    // .add(CategoryDto
    // .builder()
    // .categoryId(1L)
    // .name("Sample Category")
    // .status(useStatus.USE)
    // .build());
    return ResponseEntity.ok(categoryService.findAllCategories());
  }

  @GetMapping("/detail/{categoryId}")
  public ResponseEntity<CategoryDto> findCategoryDetail(@PathVariable Long categoryId) {
    // CategoryDto category = CategoryDto
    // .builder()
    // .categoryId(categoryId)
    // .name("Sample Category")
    // .status(useStatus.USE)
    // .build();
    return ResponseEntity.ok(categoryService.findCategory(categoryId));
  }

  @PutMapping("/change")
  public ResponseEntity<CategoryDto> changeCategory(@RequestBody CategoryDto categoryRequestDto) {
    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/detail/{categoryId}")
        .buildAndExpand(categoryRequestDto.getCategoryId())
        .toUri();
    CategoryDto categoryResponseDto = CategoryDto
        .builder()
        .categoryId(categoryRequestDto.getCategoryId())
        .name(categoryRequestDto.getName())
        .status(categoryRequestDto.getStatus())
        .build();
    return ResponseEntity.ok().location(location).body(categoryResponseDto);
  }

  @DeleteMapping("/delete/{categoryId}")
  public ResponseEntity<Long> deleteCategory(@PathVariable Long categoryId) {
    return ResponseEntity.ok(categoryId);
  }
}
