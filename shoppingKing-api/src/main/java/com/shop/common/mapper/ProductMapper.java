package com.shop.common.mapper;

import java.util.Optional;
import com.shop.category.entity.Category;
import com.shop.product.dto.ProductRequestDto;
import com.shop.product.dto.ProductResponseDto;
import com.shop.product.entity.Product;

public class ProductMapper {
  public static ProductResponseDto productToProductDto(Product product) {
    return product == null ? null
        : ProductResponseDto
            .builder()
            .productId(product.getId())
            .productName(product.getProductNm())
            .stockQuantity(product.getStockAmount())
            .salesRate(product.getSalesRate())
            .category(
                Optional.ofNullable(product.getCategory()).map(Category::getName).orElse(null))
            .price(product.getProductPrice())
            .discountRate(product.getDiscountRate())
            .discountPrice(product.getDiscountPrice())
            .registDt(product.getRegistDt())
            .deleteAt(product.getDeleteAt())
            .description(product.getDescription())
            .build();
  }

  public static Product productRequestDtoToProduct(ProductRequestDto dto, Category category) {
    return Product
        .builder()
        .productNm(dto.getProductNm())
        .productPrice(dto.getProductPrice())
        .stockAmount(dto.getStockAmount())
        .salesRate(dto.getSalesRate())
        .discountRate(dto.getDiscountRate())
        .discountPrice(dto.getDiscountPrice())
        .registDt(dto.getRegistDt())
        .description(dto.getDescription())
        .deleteAt(dto.getDeleteAt())
        .category(category)
        .build();
  }

}
