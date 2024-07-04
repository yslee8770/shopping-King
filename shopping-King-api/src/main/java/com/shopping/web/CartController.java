package com.shopping.web;

import com.shopping.dto.CartChangeDto;
import com.shopping.dto.CartResponseDto;
import com.shopping.mapper.CartMapper;
import com.shopping.service.CartService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

  private final CartService cartService;

  @GetMapping("/{memberId}")
  public ResponseEntity<List<CartResponseDto>> findCartList(@PathVariable Long memberId) {
    return ResponseEntity
        .ok(cartService
            .findCartsByMemberId(memberId)
            .stream()
            .map(CartMapper::cartToCartResponseDto)
            .collect(Collectors.toList()));
  }

  @PostMapping
  public ResponseEntity<CartResponseDto> addCart(@RequestBody CartChangeDto cartChangeDto) {
    return ResponseEntity.ok(CartMapper.cartToCartResponseDto(cartService.saveCart(cartChangeDto)));
  }

  @PatchMapping
  public ResponseEntity<CartResponseDto> changeCart(@RequestBody CartChangeDto cartChangeDto) {
    return ResponseEntity.ok(CartMapper.cartToCartResponseDto(cartService.saveCart(cartChangeDto)));
  }


  //수정
  @DeleteMapping("/{cartId}")
  public ResponseEntity<CartResponseDto> deleteCart(@PathVariable Long cartId) {
    return null;
  }
}
