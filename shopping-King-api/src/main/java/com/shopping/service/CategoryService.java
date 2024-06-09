package com.shopping.service;

import com.shopping.dto.CategoryDto;
import com.shopping.entity.Category;
import com.shopping.mapper.CategoryMapper;
import com.shopping.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  @Async("taskExecutor")
  public CompletableFuture<List<Category>> findCategoryList() {
    return CompletableFuture.completedFuture(categoryRepository.findAll());
  }

  public CompletableFuture<Category> findCategoryByCategoryId(Long categoryId) {
    return CompletableFuture.supplyAsync(() -> {
      try {
        return categoryRepository.findById(categoryId)
            .orElseThrow(
                () -> new EntityNotFoundException("Category not found with id: " + categoryId));
      } catch (Exception e) {
        throw new CompletionException(e);
      }
    });
  }

  @Async("taskExecutor")
  public CompletableFuture<Category> saveCategory(CategoryDto categoryDto) {
    return CompletableFuture.completedFuture(
        categoryRepository.save(CategoryMapper.categoryDtoToCategory(categoryDto)));
  }
}
