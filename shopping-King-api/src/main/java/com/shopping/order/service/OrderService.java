package com.shopping.order.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.shopping.order.dto.OrderRequestDto;
import com.shopping.order.entity.Orders;
import com.shopping.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
  private OrderRepository orderRepository;

  public List<Orders> findOrdersByMemberId(Long memberId) {
    return orderRepository.findByMemberId(memberId);
  }

  public OrderRequestDto findOrdersByOrderId(Long orderId) {
    return null;
  }

}
