package com.shopping.common.mapper;

import com.shopping.member.entity.Member;
import com.shopping.order.dto.OrderRequestDto;
import com.shopping.order.dto.OrderResponseDto;
import com.shopping.order.entity.Orders;
import com.shopping.product.entity.Product;

public class OrderMapper {

  public static OrderResponseDto ordertoOrderResponseDto(Orders order) {
    return OrderResponseDto
        .builder()
        .orderId(order.getId())
        .orderDt(order.getOrderDt())
        .address(order.getAddress())
        .quantity(order.getQuantity())
        .price(order.getPrice())
        .orderStatus(order.getOrderStatus())
        .product(order.getProduct())
        .build();
  }

  public static Orders orderRequestDtoToOrders(OrderRequestDto orderRequestDto, Member member,
      Product product) {
    return Orders
        .builder()
        .price(orderRequestDto.getPrice())
        .orderDt(orderRequestDto.getOrderDt())
        .quantity(orderRequestDto.getQuantity())
        .address(orderRequestDto.getAddress())
        .orderStatus(orderRequestDto.getOrderStatus())
        .member(member)
        .product(product)
        .build();
  }
}
