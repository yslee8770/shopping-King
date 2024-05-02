package com.shopping.order.web;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shopping.order.dto.OrderRequestDto;
import com.shopping.order.service.OrderService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
  private OrderService orderService;


  @GetMapping("/list/{memberId}")
  public ResponseEntity<List<OrderRequestDto>> findOrderList(@PathVariable Long memberId) {
    return ResponseEntity.ok(null);
    // return ResponseEntity.ok(orderService.findOrdersByMemberId(memberId));
  }

  @GetMapping("/detail/{orderId}")
  public ResponseEntity<OrderRequestDto> findOrderDetail(@PathVariable Long orderId) {
    // OrderDto orderResponseDto = OrderDto
    // .builder()
    // .memberId(1L)
    // .productDto(ProductDto.builder().productName("TestProduct").price(10000).build())
    // .orderedTime(LocalDateTime.now())
    // .quantity(2)
    // .price(20000)
    // .orderStatus(OrderStatus.PAYMENT)
    // .build();
    return ResponseEntity.ok(orderService.findOrdersByOrderId(orderId));
  }

  @PostMapping("/add")
  public ResponseEntity<List<OrderRequestDto>> addNewOrder(
      @RequestBody List<OrderRequestDto> orderRequestDtos) {
    return ResponseEntity.ok(null);
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
