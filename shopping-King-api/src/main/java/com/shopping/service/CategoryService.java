package com.shopping.service;

import com.shopping.dto.CategoryDto;
import com.shopping.entity.Category;
import com.shopping.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public List<Category> findCategoryList(String categoryName, int page, int size, String sort) {
    Sort.Direction sortDirection = Sort.Direction.fromString(sort);
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));

    return (categoryName != null && !categoryName.isEmpty())
        ? categoryRepository.findByNameContaining(categoryName, pageable).getContent()
        : categoryRepository.findAll(pageable).getContent();
  }

  public Category findCategoryByCategoryId(Long categoryId) {
    return categoryRepository
        .findById(categoryId)
        .orElseThrow(
            () -> new EntityNotFoundException("Category not found with id: " + categoryId));
  }

  @Transactional
  public Category updateCategory(CategoryDto categoryDto) {
    Category existingCategory = categoryRepository.findById(categoryDto.getCategoryId())
        .orElseThrow(() -> new EntityNotFoundException(
            "Category not found with id: " + categoryDto.getCategoryId()));

    existingCategory.update(categoryDto);

    return existingCategory;
  }

  @Transactional
  public void deleteCategory(Long categoryId) {
    Category existingCategory = categoryRepository.findById(categoryId)
        .orElseThrow(
            () -> new EntityNotFoundException("Category not found with id: " + categoryId));
    existingCategory.softDelete();
  }

}
