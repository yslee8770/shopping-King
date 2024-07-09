package com.shopping.service;

import com.shopping.dto.ProductRequestDTO;
import com.shopping.dto.ProductResponseDTO;
import com.shopping.entity.Category;
import com.shopping.entity.Product;
import com.shopping.exception.CustomException;
import com.shopping.mapper.ProductMapper;
import com.shopping.repository.CategoryRepository;
import com.shopping.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;

  public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
    Category category = categoryRepository.findById(productRequestDTO.getCategoryId())
        .orElseThrow(() -> new CustomException("Category not found"));
    Product product = ProductMapper.toEntity(productRequestDTO, category);
    return ProductMapper.toResponseDTO(productRepository.save(product));
  }

  public ProductResponseDTO updateProduct(Long productId, ProductRequestDTO productRequestDTO) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new CustomException("Product not found"));
    Category category = categoryRepository.findById(productRequestDTO.getCategoryId())
        .orElseThrow(() -> new CustomException("Category not found"));
    product.update(productRequestDTO.getName(), productRequestDTO.getDescription(), productRequestDTO.getPrice(), category);
    return ProductMapper.toResponseDTO(productRepository.save(product));
  }

  public void deleteProduct(Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new CustomException("Product not found"));
    product.delete();
    productRepository.save(product);
  }

  public ProductResponseDTO getProductById(Long productId) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new CustomException("Product not found"));
    return ProductMapper.toResponseDTO(product);
  }

  public List<ProductResponseDTO> getAllProducts() {
    return productRepository.findAll().stream()
        .map(ProductMapper::toResponseDTO)
        .collect(Collectors.toList());
  }
}
