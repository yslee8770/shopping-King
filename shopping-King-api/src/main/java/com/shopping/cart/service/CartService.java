package com.shopping.cart.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.shopping.cart.dto.CartResponseDto;
import com.shopping.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
  private final CartRepository cartRepository;

  public List<CartResponseDto> findCartsByMemberId(Long memberId) {
    return null;
  }


}
