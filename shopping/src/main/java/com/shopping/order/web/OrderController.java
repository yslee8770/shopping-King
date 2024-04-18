package com.shopping.order.web;

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


@RestController
@RequestMapping("/order")
public class OrderController {
	
	//주문
    @PostMapping("/add")
	public ResponseEntity<List<OrderDto>> createNewOrder(@RequestBody List<OrderDto> orderRequestDtos) {
    	//저장 로직
    	List<OrderDto> ordeResponseDtos= new ArrayList<OrderDto>();
		return ResponseEntity.ok(ordeResponseDtos);
    }
    //주문내역 조회
    @GetMapping("/list/{memberId}")
    public ResponseEntity<List<OrderDto>> findOrderList(@PathVariable Long memberId){
    	List<OrderDto> orderDtos= new ArrayList<OrderDto>();
    	return ResponseEntity.ok(orderDtos);
    }
    //주문내역 상세 조회
    @GetMapping("/detail/{orderId}")
    public ResponseEntity<OrderDto> findOrderDetail(@PathVariable Long orderId){
    	OrderDto orderResponseDto= new OrderDto();
    	return ResponseEntity.ok(orderResponseDto);
    }
    //주문상태변경
	@PatchMapping("change/{orderId}/{orderStatus}")
	public ResponseEntity<OrderDto> changeOrderStatus(@PathVariable Long orderId, @PathVariable OrderStatus orderStatus) {
		OrderDto orderResponseDto= new OrderDto();
		return ResponseEntity.ok(orderResponseDto);
	}
	//주문수량변경
	@PatchMapping("change/{orderId}/{quantity}")
	public ResponseEntity<OrderDto> changeOrderQuantity(@PathVariable Long orderId, @PathVariable int quantity) {
		OrderDto orderResponseDto= new OrderDto();
		return ResponseEntity.ok(orderResponseDto);
	}
    //주문취소
	@DeleteMapping("cancel/{orderId}")
	public ResponseEntity<Integer> deleteOrder(@PathVariable Long orderId) {
		return ResponseEntity.noContent().build();
	}

}
