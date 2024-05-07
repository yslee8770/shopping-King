package com.shop.cart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.shop.cart.dto.CartChangeDto;
import com.shop.cart.entity.Cart;
import com.shop.cart.repository.CartRepository;
import com.shop.cart.service.CartService;
import com.shop.common.DeleteAt;
import com.shop.product.entity.Product;
import com.shop.product.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class CartServiceUnitTest {

  @InjectMocks
  private CartService cartService;

  @Mock
  private CartRepository cartRepository;

  @Mock
  private ProductRepository productRepository;

  @Test
  void testFindCartsByMemberId() {
    Long memberId = 1L;
    List<Cart> expectedCarts = new ArrayList<>();
    when(cartRepository.findByMemberId(memberId)).thenReturn(expectedCarts);

    List<Cart> actualCarts = cartService.findCartsByMemberId(memberId);

    assertEquals(expectedCarts, actualCarts);
  }

  @Test
  void testSaveCart() {
    CartChangeDto cartChangeDto = CartChangeDto
        .builder()
        .memberId(1L)
        .productId(1L)
        .quantity(2)
        .pricce(100)
        .deleteAt(DeleteAt.N)
        .build();

    Product product = Product.builder().id(cartChangeDto.getProductId()).build();

    when(productRepository.findById(cartChangeDto.getProductId())).thenReturn(Optional.of(product));

    Cart expectedCart = Cart
        .builder()
        .memberId(cartChangeDto.getMemberId())
        .product(product)
        .quantity(cartChangeDto.getQuantity())
        .price(cartChangeDto.getPricce())
        .deleteAt(cartChangeDto.getDeleteAt())
        .build();

    when(cartRepository.save(any(Cart.class))).thenReturn(expectedCart);

    Cart savedCart = cartService.saveCart(cartChangeDto);

    assertEquals(expectedCart, savedCart);
  }
}
