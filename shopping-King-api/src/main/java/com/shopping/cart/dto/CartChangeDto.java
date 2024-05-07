package com.shopping.cart.dto;

import com.shopping.common.DeleteAt;
import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CartChangeDto {
  @Nullable
  private Long cartId;
  private Long memberId;
  private Long productId;
  private int quantity;
  private int pricce;
  private DeleteAt deleteAt;
}
