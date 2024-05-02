package com.shopping.cart.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.shopping.cart.dto.CartChangeDto;
import com.shopping.cart.entity.Cart;
import com.shopping.cart.repository.CartRepository;
import com.shopping.common.mapper.CartMapper;
import com.shopping.product.entity.Product;
import com.shopping.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
  private final CartRepository cartRepository;
  private final ProductRepository productRepository;

  public List<Cart> findCartsByMemberId(Long memberId) {
    return cartRepository.findByMemberId(memberId);
  }

  public Cart saveCart(CartChangeDto cartChangeDto) {
    Product product = productRepository
        .findById(cartChangeDto.getProductId())
        .orElseThrow(() -> new RuntimeException(
            "Product not found with id: " + cartChangeDto.getProductId()));
    return cartRepository.save(CartMapper.cartChangeDtoToCart(cartChangeDto, product));
  }


}
