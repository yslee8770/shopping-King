package com.shopping.category.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.shopping.category.dto.CategoryDto;
import com.shopping.category.entity.Category;
import com.shopping.category.repository.CategoryRepository;
import com.shopping.common.mapper.CategoryMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
  private CategoryRepository categoryRepository;

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
