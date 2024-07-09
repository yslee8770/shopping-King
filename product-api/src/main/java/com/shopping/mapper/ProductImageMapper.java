package com.shopping.mapper;

import com.shopping.dto.ProductImageRequestDTO;
import com.shopping.dto.ProductImageResponseDTO;
import com.shopping.entity.ProductImage;

public class ProductImageMapper {
  public static ProductImageResponseDTO toResponseDTO(ProductImage productImage) {
    return ProductImageResponseDTO.builder()
        .id(productImage.getId())
        .url(productImage.getUrl())
        .build();
  }

  public static ProductImage toEntity(ProductImageRequestDTO productImageRequestDTO) {
    return ProductImage.builder()
        .url(productImageRequestDTO.getUrl())
        .build();
  }
}
