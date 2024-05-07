package com.shopping.order.dto;

import java.time.LocalDateTime;
import com.shopping.common.OrderStatus;
import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {

  @Nullable
  private Long orderId;

  private LocalDateTime orderDt;

  private String address;

  private int quantity;

  private int price;

  private OrderStatus orderStatus;

  private Long memberId;

  private Long productId;

}
