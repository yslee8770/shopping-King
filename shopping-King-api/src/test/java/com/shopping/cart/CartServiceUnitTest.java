package com.shopping.cart;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.shopping.cart.service.CartService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.shopping.cart.dto.CartChangeDto;
import com.shopping.cart.entity.Cart;
import com.shopping.cart.repository.CartRepository;
import com.shopping.common.DeleteAt;
import com.shopping.product.entity.Product;
import com.shopping.product.repository.ProductRepository;

@SpringBootTest
class CartServiceUnitTest {

  @Mock
  private CartRepository cartRepository;

  @Mock
  private ProductRepository productRepository;

  @InjectMocks
  private CartService cartService;

  @Test
  @DisplayName("카트 조회")
  void findCartsByMemberId() {
    // Mock data
    Long memberId = 1L;
    Cart cart1 = Cart.builder().id(1L).memberId(memberId).build();
    Cart cart2 = Cart.builder().id(2L).memberId(memberId).build();
    List<Cart> carts = Arrays.asList(cart1, cart2);

    // Stubbing the repository method
    when(cartRepository.findByMemberId(memberId)).thenReturn(carts);

    // Call the service method
    List<Cart> result = cartService.findCartsByMemberId(memberId);

    // Verify the result
    assertEquals(carts, result);
  }

  @Test
  @DisplayName("카트 저장")
  void saveCart() {
    // Mock data
    Long productId = 1L;
    Long memberId = 1L;
    CartChangeDto cartChangeDto = CartChangeDto.builder()
            .productId(productId)
            .memberId(memberId)
            .quantity(2)
            .price(100)
            .deleteAt(DeleteAt.N)
            .build();
    Product product = Product.builder().id(productId).build();
    Cart cart = Cart.builder()
            .id(1L)
            .memberId(memberId)
            .product(product)
            .quantity(2)
            .price(100)
            .deleteAt(DeleteAt.N)
            .build();

    // Stubbing the repository methods
    when(productRepository.findById(productId)).thenReturn(Optional.of(product));
    when(cartRepository.save(any(Cart.class))).thenReturn(cart);

    // Call the service method
    Cart savedCart = cartService.saveCart(cartChangeDto);

    // Verify the result
    assertNotNull(savedCart);
    assertEquals(cart.getId(), savedCart.getId());
    assertEquals(cart.getMemberId(), savedCart.getMemberId());
    assertEquals(cart.getProduct(), savedCart.getProduct());
    assertEquals(cart.getQuantity(), savedCart.getQuantity());
    assertEquals(cart.getPrice(), savedCart.getPrice());
    assertEquals(cart.getDeleteAt(), savedCart.getDeleteAt());
  }
}
