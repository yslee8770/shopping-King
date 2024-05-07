package com.shop.order.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.shop.common.mapper.OrderMapper;
import com.shop.member.repository.MemberRepository;
import com.shop.order.dto.OrderRequestDto;
import com.shop.order.entity.Orders;
import com.shop.order.repository.OrderRepository;
import com.shop.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
  private OrderRepository orderRepository;
  private final MemberRepository memberRepository;
  private final ProductRepository productRepository;

  public List<Orders> findOrdersByMemberId(Long memberId) {
    return Optional
        .ofNullable(orderRepository.findByMemberId(memberId))
        .orElseThrow(() -> new RuntimeException("Order not found with memberId: " + memberId));
  }

  public Orders findOrdersByOrderId(Long orderId) {
    return orderRepository
        .findById(orderId)
        .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));
  }

  public Orders changeOrder(OrderRequestDto orderRequestDto) {
    return orderRepository
        .save(OrderMapper
            .orderRequestDtoToOrders(orderRequestDto,
                memberRepository
                    .findById(orderRequestDto.getMemberId())
                    .orElseThrow(() -> new EntityNotFoundException(
                        "Member not found with id: " + orderRequestDto.getMemberId())),
                productRepository
                    .findById(orderRequestDto.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException(
                        "Product not found with id: " + orderRequestDto.getProductId()))));
  }
}
