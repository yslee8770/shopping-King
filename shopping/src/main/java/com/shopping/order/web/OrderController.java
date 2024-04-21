package com.shopping.order.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.common.OrderStatus;
import com.shopping.order.dto.OrderDto;
import com.shopping.product.dto.ProductDto;


@RestController
@RequestMapping("/order")
public class OrderController {
	
	//주문
    @PostMapping("/add")
	public ResponseEntity<List<OrderDto>> createNewOrder(@RequestBody List<OrderDto> orderRequestDtos) {
    	//저장 로직
    	List<OrderDto> ordeResponseDtos= new ArrayList<OrderDto>();
    	ordeResponseDtos.add(OrderDto.builder()
                .memberId(1L)
                .productDto(ProductDto.builder()
                        .productName("TestProduct")
                        .price(10000)
                        .build())
                .orderedTime(LocalDateTime.now())
                .quantity(2)
                .price(20000)
                .orderStatus(OrderStatus.PAYMENT)
                .build());
		return ResponseEntity.ok(ordeResponseDtos);
    }
    //주문내역 조회
    @GetMapping("/list/{memberId}")
    public ResponseEntity<List<OrderDto>> findOrderList(@PathVariable Long memberId){
    	List<OrderDto> orderDtos= new ArrayList<OrderDto>();
        orderDtos.add(OrderDto.builder()
                .memberId(memberId)
                .productDto(ProductDto.builder()
                        .productName("TestProduct")
                        .price(10000)
                        .build())
                .orderedTime(LocalDateTime.now())
                .quantity(2)
                .price(20000)
                .orderStatus(OrderStatus.PAYMENT)
                .build());
    	return ResponseEntity.ok(orderDtos);
    }
    //주문내역 상세 조회
    @GetMapping("/detail/{orderId}")
    public ResponseEntity<OrderDto> findOrderDetail(@PathVariable Long orderId){
        OrderDto orderResponseDto = OrderDto.builder()
                .memberId(1L)
                .productDto(ProductDto.builder()
                        .productName("TestProduct")
                        .price(10000)
                        .build())
                .orderedTime(LocalDateTime.now())
                .quantity(2)
                .price(20000)
                .orderStatus(OrderStatus.PAYMENT)
                .build();
    	return ResponseEntity.ok(orderResponseDto);
    }
    //주문상태변경
	@PatchMapping("changeOrderStatus/{orderId}/{orderStatus}")
	public ResponseEntity<OrderDto> changeOrderStatus(@PathVariable Long orderId, @PathVariable OrderStatus orderStatus) {
        OrderDto orderResponseDto = OrderDto.builder()
                .memberId(1L)
                .productDto(ProductDto.builder()
                        .productName("TestProduct")
                        .price(10000)
                        .build())
                .orderedTime(LocalDateTime.now())
                .quantity(2)
                .price(20000)
                .orderStatus(orderStatus)
                .build();
		return ResponseEntity.ok(orderResponseDto);
	}
	//주문수량변경
	@PatchMapping("changeQuantity/{orderId}/{quantity}")
	public ResponseEntity<OrderDto> changeOrderQuantity(@PathVariable Long orderId, @PathVariable int quantity) {
        OrderDto orderResponseDto = OrderDto.builder()
                .memberId(1L)
                .productDto(ProductDto.builder()
                        .productName("TestProduct")
                        .price(10000)
                        .build())
                .orderedTime(LocalDateTime.now())
                .quantity(quantity)
                .price(20000 * quantity)
                .orderStatus(OrderStatus.PAYMENT)
                .build();
		return ResponseEntity.ok(orderResponseDto);
	}
    //주문취소
	@DeleteMapping("cancel/{orderId}")
	public ResponseEntity<Integer> deleteOrder(@PathVariable Long orderId) {
		return ResponseEntity.noContent().build();
	}

}
