package com.shop.category.dto;

import com.shop.common.DeleteAt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
  private Long categoryId;
  private String name;
  private DeleteAt deleteAt;
}
