package com.shopping.order.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.shopping.order.dto.OrderDto;
import com.shopping.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
  private OrderRepository orderRepository;

  public List<OrderDto> findOrdersByMemberId(Long memberId) {
    return null;
  }

  public OrderDto findOrdersByOrderId(Long orderId) {
    return null;
  }

}
