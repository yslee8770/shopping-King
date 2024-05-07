package com.shop.order.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shop.common.mapper.OrderMapper;
import com.shop.order.dto.OrderRequestDto;
import com.shop.order.dto.OrderResponseDto;
import com.shop.order.service.OrderService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
  private OrderService orderService;


  @GetMapping("/list/{memberId}")
  public ResponseEntity<List<OrderResponseDto>> findOrderList(@PathVariable Long memberId) {
    return ResponseEntity
        .ok(orderService
            .findOrdersByMemberId(memberId)
            .stream()
            .map(OrderMapper::ordertoOrderResponseDto)
            .collect(Collectors.toList()));
  }

  @GetMapping("/detail/{orderId}")
  public ResponseEntity<OrderResponseDto> findOrderDetail(@PathVariable Long orderId) {
    return ResponseEntity
        .ok(OrderMapper.ordertoOrderResponseDto(orderService.findOrdersByOrderId(orderId)));
  }

  @PostMapping("/add")
  public ResponseEntity<List<OrderResponseDto>> addOrder(
      @RequestBody List<OrderRequestDto> orderRequestDtos) {
    List<OrderResponseDto> responseList = new ArrayList<OrderResponseDto>();
    for (OrderRequestDto orderRequestDto : orderRequestDtos) {
      responseList
          .add(OrderMapper.ordertoOrderResponseDto(orderService.changeOrder(orderRequestDto)));
    }
    return ResponseEntity.ok(responseList);
  }

  @PostMapping("/change")
  public ResponseEntity<List<OrderRequestDto>> changeOrder(
      @RequestBody List<OrderRequestDto> orderRequestDtos) {
    return ResponseEntity.ok(null);
  }


  @DeleteMapping("cancel/{orderId}")
  public ResponseEntity<Long> deleteOrder(@PathVariable Long orderId) {
    return ResponseEntity.ok(orderId);
  }

}
