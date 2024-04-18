package com.shopping.cart.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.cart.dto.CartResponseDto;

@RestController
@RequestMapping("/cart")
public class CartController {
	@GetMapping("/list/{memberId}")
	public ResponseEntity<List<CartResponseDto>> cartList(@PathVariable Long memberId){
		List<CartResponseDto> cartDtos= new ArrayList<CartResponseDto>();
		return ResponseEntity.ok(cartDtos);
	}
	@PostMapping("/add/{memberId}/{productId}")
	public ResponseEntity<List<CartResponseDto>> addCart(@PathVariable Long memberId, @PathVariable Long productId){
		//insert 로직
		
		List<CartResponseDto> cartDtos= new ArrayList<CartResponseDto>();
		return ResponseEntity.ok(cartDtos);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<CartResponseDto> deleteCart(@RequestParam List<Long> productsIds){
		//delete 로직
		
		return ResponseEntity.noContent().build();
	}
}
