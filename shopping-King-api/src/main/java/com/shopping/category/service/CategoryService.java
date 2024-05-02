package com.shopping.category.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.shopping.category.dto.CategoryDto;
import com.shopping.category.repository.CategoryRepository;
import com.shopping.common.CommonNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
  private CategoryRepository categoryRepository;

  public List<CategoryDto> findAllCategories() {
    return categoryRepository
        .findAllCategories()
        .stream()
        .map(category -> CategoryDto
            .builder()
            .categoryId(category.getCategoryId())
            .name(category.getName())
            .status(category.getStatus())
            .build())
        .collect(Collectors.toList());
  }

  public CategoryDto findCategory(Long categoryId) {
    return categoryRepository
        .findById(categoryId)
        .map(category -> CategoryDto
            .builder()
            .categoryId(category.getCategoryId())
            .name(category.getName())
            .status(category.getStatus())
            .build())
        .orElseThrow(() -> new CommonNotFoundException(categoryId));
  }
}
