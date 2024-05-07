package com.shop.cart.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.shop.cart.dto.CartChangeDto;
import com.shop.cart.entity.Cart;
import com.shop.cart.repository.CartRepository;
import com.shop.common.mapper.CartMapper;
import com.shop.product.entity.Product;
import com.shop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
  private final CartRepository cartRepository;
  private final ProductRepository productRepository;

  public List<Cart> findCartsByMemberId(Long memberId) {
    return Optional
        .ofNullable(cartRepository.findByMemberId(memberId))
        .orElseThrow(() -> new RuntimeException("Cart not found for member with id: " + memberId));
  }

  public Cart saveCart(CartChangeDto cartChangeDto) {
    Product product = productRepository
        .findById(cartChangeDto.getProductId())
        .orElseThrow(() -> new RuntimeException(
            "Product not found with id: " + cartChangeDto.getProductId()));
    return cartRepository.save(CartMapper.cartChangeDtoToCart(cartChangeDto, product));
  }
}
