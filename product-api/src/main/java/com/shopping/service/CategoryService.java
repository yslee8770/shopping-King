package com.shopping.service;

import com.shopping.dto.CategoryRequestDTO;
import com.shopping.dto.CategoryResponseDTO;
import com.shopping.entity.Category;
import com.shopping.exception.CustomException;
import com.shopping.mapper.CategoryMapper;
import com.shopping.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
    Category category = CategoryMapper.toEntity(categoryRequestDTO);
    return CategoryMapper.toResponseDTO(categoryRepository.save(category));
  }

  public CategoryResponseDTO updateCategory(Long categoryId, CategoryRequestDTO categoryRequestDTO) {
    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new CustomException("Category not found"));
    category.update(categoryRequestDTO.getName());
    return CategoryMapper.toResponseDTO(categoryRepository.save(category));
  }

  public void deleteCategory(Long categoryId) {
    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new CustomException("Category not found"));
    category.delete();
    categoryRepository.save(category);
  }

  public CategoryResponseDTO getCategoryById(Long categoryId) {
    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new CustomException("Category not found"));
    return CategoryMapper.toResponseDTO(category);
  }

  public List<CategoryResponseDTO> getAllCategories() {
    return categoryRepository.findAll().stream()
        .map(CategoryMapper::toResponseDTO)
        .collect(Collectors.toList());
  }
}
