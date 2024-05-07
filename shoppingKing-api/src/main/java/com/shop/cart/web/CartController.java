package com.shop.cart.web;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shop.cart.dto.CartChangeDto;
import com.shop.cart.dto.CartResponseDto;
import com.shop.cart.service.CartService;
import com.shop.common.mapper.CartMapper;
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
  @PostMapping("/change")
  public ResponseEntity<CartResponseDto> changeCart(@RequestBody CartChangeDto cartChangeDto) {
    return ResponseEntity.ok(CartMapper.cartToCartResponseDto(cartService.saveCart(cartChangeDto)));
  }

  @DeleteMapping("/delete")
  public ResponseEntity<CartResponseDto> deleteCart(@RequestParam CartChangeDto cartChangeDto) {
    return ResponseEntity.ok(CartMapper.cartToCartResponseDto(cartService.saveCart(cartChangeDto)));
  }
}
