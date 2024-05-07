package com.shop.common.mapper;

import com.shop.category.dto.CategoryDto;
import com.shop.category.entity.Category;

public class CategoryMapper {
  public static CategoryDto categoryToCategoryeDto(Category category) {
    return category == null ? null
        : CategoryDto
            .builder()
            .categoryId(category.getId())
            .name(category.getName())
            .deleteAt(category.getDeleteAt())
            .build();
  }

  public static Category categoryDtoToCategory(CategoryDto categoryDto) {
    return categoryDto == null ? null
        : Category
            .builder()
            .Id(categoryDto.getCategoryId())
            .name(categoryDto.getName())
            .deleteAt(categoryDto.getDeleteAt())
            .build();
  }
}
