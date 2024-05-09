package com.shopping.common.mapper;

import com.shopping.cart.dto.CartChangeDto;
import com.shopping.cart.dto.CartResponseDto;
import com.shopping.cart.entity.Cart;
import com.shopping.product.entity.Product;

public class CartMapper {
  public static CartResponseDto cartToCartResponseDto(Cart cart) {
    return cart == null || cart.getProduct()==null ? null
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
    return cartChangeDto == null || product==null ? null
        : Cart
            .builder()
            .memberId(cartChangeDto.getMemberId())
            .product(product)
            .quantity(cartChangeDto.getQuantity())
            .price(cartChangeDto.getPrice())
            .build();
  }
}
