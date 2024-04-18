package com.shopping.order.dto;

import java.time.LocalDateTime;

import com.shopping.common.OrderStatus;
import com.shopping.product.dto.ProductDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor (access = AccessLevel.PRIVATE)
public class OrderDto {
	//유저id
	private long memberId;
	//상품
	private ProductDto productDto;
    //주문시간
    private LocalDateTime orderedTime;
    //수량
    private int quantity;
    //가격
    private int price;
    //주문상태
    private OrderStatus orderStatus;
    
    public int getPrice() {
    	return productDto.getPrice()*quantity;
    }
}
