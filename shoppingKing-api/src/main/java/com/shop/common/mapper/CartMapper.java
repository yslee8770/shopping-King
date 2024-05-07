package com.shop.common.mapper;

import com.shop.cart.dto.CartChangeDto;
import com.shop.cart.dto.CartResponseDto;
import com.shop.cart.entity.Cart;
import com.shop.product.entity.Product;

public class CartMapper {
  public static CartResponseDto cartToCartResponseDto(Cart cart) {
    return cart == null ? null
        : CartResponseDto
            .builder()
            .cartId(cart.getId())
            .memberId(cart.getId())
            .quantity(cart.getQuantity())
            .price(cart.getProduct().getProductPrice())
            .productId(cart.getProduct().getId())
            .productNm(cart.getProduct().getProductNm())
            .productDiscountPrice(cart.getProduct().getDiscountPrice())
            .productDiscountRate(cart.getProduct().getDiscountRate())
            .productPrice(cart.getProduct().getProductPrice())
            .build();
  }

  public static Cart cartChangeDtoToCart(CartChangeDto cartChangeDto, Product product) {
    return cartChangeDto == null ? null
        : Cart
            .builder()
            .memberId(cartChangeDto.getMemberId())
            .product(product)
            .quantity(cartChangeDto.getQuantity())
            .price(cartChangeDto.getPricce())
            .build();
  }
}
