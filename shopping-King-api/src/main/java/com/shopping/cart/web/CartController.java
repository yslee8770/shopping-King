package com.shopping.cart.web;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.shopping.cart.dto.CartChangeDto;
import com.shopping.cart.dto.CartResponseDto;
import com.shopping.cart.service.CartService;
import com.shopping.common.mapper.CartMapper;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

  private final CartService cartService;

  @GetMapping("/list/{memberId}")
  public ResponseEntity<List<CartResponseDto>> findCartList(@PathVariable Long memberId) {
    return ResponseEntity
        .ok(cartService
            .findCartsByMemberId(memberId)
            .stream()
            .map(CartMapper::cartToCartResponseDto)
            .collect(Collectors.toList()));
  }

  @PostMapping("/add")
  public ResponseEntity<CartResponseDto> addCart(@RequestBody CartChangeDto cartChangeDto) {
    return ResponseEntity.ok(CartMapper.cartToCartResponseDto(cartService.saveCart(cartChangeDto)));
  }
  @PutMapping("/change")
  public ResponseEntity<CartResponseDto> changeCart(@RequestBody CartChangeDto cartChangeDto) {
    return ResponseEntity.ok(CartMapper.cartToCartResponseDto(cartService.saveCart(cartChangeDto)));
  }

  @DeleteMapping("/delete")
  public ResponseEntity<CartResponseDto> deleteCart(@RequestParam CartChangeDto cartChangeDto) {
    return ResponseEntity.ok(CartMapper.cartToCartResponseDto(cartService.saveCart(cartChangeDto)));
  }
}
