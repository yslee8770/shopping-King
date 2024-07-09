package com.shopping.mapper;

import com.shopping.dto.ProductRequestDTO;
import com.shopping.dto.ProductResponseDTO;
import com.shopping.entity.Product;
import com.shopping.entity.Category;

import java.util.stream.Collectors;

public class ProductMapper {
  public static ProductResponseDTO toResponseDTO(Product product) {
    return ProductResponseDTO.builder()
        .id(product.getId())
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .category(product.getCategory() != null ? CategoryMapper.toResponseDTO(product.getCategory()) : null)
        .inventory(InventoryMapper.toResponseDTO(product.getInventory()))
        .images(product.getImages() != null ? product.getImages().stream()
            .map(ProductImageMapper::toResponseDTO)
            .collect(Collectors.toList()) : null)
        .build();
  }

  public static Product toEntity(ProductRequestDTO productRequestDTO, Category category) {
    return Product.builder()
        .name(productRequestDTO.getName())
        .description(productRequestDTO.getDescription())
        .price(productRequestDTO.getPrice())
        .category(category)
        .build();
  }
}
