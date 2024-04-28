package com.shopping.cart.web;

import java.time.LocalDateTime;
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
import com.shopping.cart.service.CartService;
import com.shopping.common.ProductStatus;
import com.shopping.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

  private final CartService cartService;

  @GetMapping("/list/{memberId}")
  public ResponseEntity<List<CartResponseDto>> findCartList(@PathVariable Long memberId) {
    // List<CartResponseDto> cartDtos = new ArrayList<CartResponseDto>();
    // ProductDto productDto = ProductDto
    // .builder()
    // .productName("Sample Product")
    // .productId(1L)
    // .stockQuantity(10)
    // .salesRate(5)
    // .category("Sample Category")
    // .price(12000)
    // .discountRate(20)
    // .registrationDate(LocalDateTime.now())
    // .statusCode(ProductStatus.SALE)
    // .description("Sample Description")
    // .build();
    // CartResponseDto cartResponseDto = CartResponseDto
    // .builder()
    // .cartId(1L)
    // .productDto(productDto)
    // .memberId(memberId)
    // .quantity(2)
    // .price(productDto.getPrice() * 2)
    // .build();
    // cartDtos.add(cartResponseDto);

    return ResponseEntity.ok(cartService.findCartsByMemberId(memberId));
  }

  @PostMapping("/add/{memberId}/{productId}")
  public ResponseEntity<List<CartResponseDto>> addCart(@PathVariable Long memberId,
      @PathVariable Long productId) {

    List<CartResponseDto> cartDtos = new ArrayList<CartResponseDto>();
    ProductDto productDto = ProductDto
        .builder()
        .productName("Sample Product")
        .productId(1L)
        .stockQuantity(10)
        .salesRate(5)
        .category("Sample Category")
        .price(12000)
        .discountRate(20)
        .registrationDate(LocalDateTime.now())
        .statusCode(ProductStatus.SALE)
        .description("Sample Description")
        .build();
    CartResponseDto cartResponseDto = CartResponseDto
        .builder()
        .cartId(1L)
        .productDto(productDto)
        .memberId(memberId)
        .quantity(2)
        .price(productDto.getPrice() * 2)
        .build();

    cartDtos.add(cartResponseDto);
    return ResponseEntity.ok(cartDtos);
  }

  @DeleteMapping("/delete")
  public ResponseEntity<List<Long>> deleteCart(@RequestParam List<Long> productsIds) {
    return ResponseEntity.ok(productsIds);
  }
}
