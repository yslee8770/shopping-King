package com.shop.cart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import com.shop.cart.dto.CartChangeDto;
import com.shop.cart.entity.Cart;
import com.shop.cart.repository.CartRepository;
import com.shop.cart.service.CartService;
import com.shop.common.DeleteAt;
import com.shop.product.entity.Product;
import com.shop.product.repository.ProductRepository;

@DataJpaTest
@MockBeans({@MockBean(ProductRepository.class)})
public class CartServiceIntegrationTest {

  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private ProductRepository productRepository;

  private CartService cartService;

  @BeforeEach
  void setUp() {
    cartService = new CartService(cartRepository, productRepository);
  }

  @Test
  void testFindCartsByMemberId() {
    Long memberId = 1L;
    List<Cart> expectedCarts = List
        .of(Cart.builder().memberId(memberId).build(), Cart.builder().memberId(memberId).build());
    expectedCarts.forEach(cartRepository::save);

    List<Cart> actualCarts = cartService.findCartsByMemberId(memberId);

    assertEquals(expectedCarts.size(), actualCarts.size());
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

    productRepository.save(product);

    Cart savedCart = cartService.saveCart(cartChangeDto);

    assertEquals(cartChangeDto.getMemberId(), savedCart.getMemberId());
  }
}
