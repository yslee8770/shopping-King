package com.shopping.order.dto;

import java.time.LocalDateTime;
import com.shopping.common.OrderStatus;
import com.shopping.product.entity.Product;

public class OrderResponseDtd {

  private Long orderId;

  private LocalDateTime ordereDt;

  private String address;

  private int quantity;

  private int price;

  private OrderStatus orderStatus;

  private Product product;
}
