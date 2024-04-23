package com.shopping.cart.dto;

import com.shopping.product.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponseDto {
  private Long cartId;
  private ProductDto productDto;
  private Long memberId;
  // 수량
  private int quantity;
  // 가격
  private int price;

  public int getPrice() {
    if (productDto != null) {
      return productDto.getPrice() * quantity;
    } else {
      return 0;
    }
  }
}
