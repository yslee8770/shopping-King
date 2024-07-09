package com.shopping.mapper;

import com.shopping.dto.CategoryRequestDTO;
import com.shopping.dto.CategoryResponseDTO;
import com.shopping.entity.Category;

public class CategoryMapper {
  public static CategoryResponseDTO toResponseDTO(Category category) {
    return CategoryResponseDTO.builder()
        .id(category.getId())
        .name(category.getName())
        .build();
  }

  public static Category toEntity(CategoryRequestDTO categoryRequestDTO) {
    return Category.builder()
        .name(categoryRequestDTO.getName())
        .build();
  }
}
