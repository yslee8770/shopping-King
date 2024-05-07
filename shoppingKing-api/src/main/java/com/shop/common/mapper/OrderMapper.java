package com.shop.common.mapper;

import com.shop.member.entity.Member;
import com.shop.order.dto.OrderRequestDto;
import com.shop.order.dto.OrderResponseDto;
import com.shop.order.entity.Orders;
import com.shop.product.entity.Product;

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
