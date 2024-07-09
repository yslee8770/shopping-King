package com.shopping.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
  private Long id;
  private String name;
  private String description;
  private double price;
  private CategoryResponseDTO category;
  private InventoryResponseDTO inventory;
  private List<ProductImageResponseDTO> images;
}
