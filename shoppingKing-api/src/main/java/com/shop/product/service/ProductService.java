package com.shop.product.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.shop.category.repository.CategoryRepository;
import com.shop.common.mapper.ProductMapper;
import com.shop.product.dto.ProductRequestDto;
import com.shop.product.entity.Product;
import com.shop.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;

  public List<Product> findCategoryList(Long categoryId) {
    return

    (categoryId == null)
        ? Optional
            .ofNullable(productRepository.findAll())
            .orElseThrow(() -> new RuntimeException("No Product "))
        : Optional
            .ofNullable(productRepository.findAllByCategoryId(categoryId))
            .orElseThrow(
                () -> new RuntimeException("Product not found with categoryId: " + categoryId));
  }

  public Product findProudctByProductId(Long productId) {
    return productRepository
        .findById(productId)
        .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
  }

  public Product changeProduct(ProductRequestDto productRequestDto) {
    return productRepository
        .save(ProductMapper
            .productRequestDtoToProduct(productRequestDto,
                categoryRepository
                    .findById(productRequestDto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException(
                        "Category not found with id: " + productRequestDto.getCategoryId()))));
  }
}
