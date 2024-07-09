package com.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
  private String name;
  private String description;
  private double price;
  private Long categoryId;
}