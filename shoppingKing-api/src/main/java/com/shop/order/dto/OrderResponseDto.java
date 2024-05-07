package com.shop.order.dto;

import java.time.LocalDateTime;
import com.shop.common.OrderStatus;
import com.shop.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {

  private Long orderId;

  private LocalDateTime orderDt;

  private String address;

  private int quantity;

  private int price;

  private OrderStatus orderStatus;

  private Product product;
}
