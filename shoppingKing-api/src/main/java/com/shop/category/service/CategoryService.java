package com.shop.category.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.shop.category.dto.CategoryDto;
import com.shop.category.entity.Category;
import com.shop.category.repository.CategoryRepository;
import com.shop.common.mapper.CategoryMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryRepository categoryRepository;

  public List<Category> findCategoryList() {
    return Optional
        .ofNullable(categoryRepository.findAll())
        .orElseThrow(() -> new RuntimeException("No Category"));
  }

  public Category findCategoryByCategoryId(Long categoryId) {
    return categoryRepository
        .findById(categoryId)
        .orElseThrow(
            () -> new EntityNotFoundException("Category not found with id: " + categoryId));
  }

  public Category saveCategory(CategoryDto categoryDto) {
    return categoryRepository.save(CategoryMapper.categoryDtoToCategory(categoryDto));
  }
}
