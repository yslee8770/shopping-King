package com.shopping.order;

import static org.hamcrest.Matchers.is;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.common.OrderStatus;
import com.shopping.order.dto.OrderDto;
import com.shopping.product.dto.ProductDto;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  private OrderDto testOrder;

  @BeforeEach
  public void setup() {
    ProductDto productDto = ProductDto.builder().productName("TestProduct").price(10000).build();

    testOrder = OrderDto
        .builder()
        .memberId(1L)
        .productDto(productDto)
        .orderedTime(LocalDateTime.now())
        .quantity(2)
        .price(20000)
        .orderStatus(OrderStatus.PAYMENT)
        .build();
  }

  @Test
  public void testCreateNewOrder() throws Exception {
    List<OrderDto> orderList = new ArrayList<>();
    orderList.add(testOrder);

    mockMvc
        .perform(MockMvcRequestBuilders
            .post("/order/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(orderList)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].memberId", is(1)))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.[0].productDto.productName", is("TestProduct")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].quantity", is(2)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].price", is(20000)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].orderStatus", is("PAYMENT")));
  }

  @Test
  public void testFindOrderList() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/order/list/{memberId}", testOrder.getMemberId()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].memberId", is(1)))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.[0].productDto.productName", is("TestProduct")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].quantity", is(2)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].price", is(20000)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].orderStatus", is("PAYMENT")));
  }

  @Test
  public void testFindOrderDetail() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/order/detail/{orderId}", 1L))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.memberId", is(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.productDto.productName", is("TestProduct")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.quantity", is(2)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.price", is(20000)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.orderStatus", is("PAYMENT")));
  }

  @Test
  public void testChangeOrderStatus() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders
            .patch("/order/changeOrderStatus/{orderId}/{orderStatus}", 1L, OrderStatus.SHIPPING))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.memberId", is(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.productDto.productName", is("TestProduct")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.quantity", is(2)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.price", is(20000)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.orderStatus", is("SHIPPING")));
  }

  @Test
  public void testChangeOrderQuantity() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.patch("/order/changeQuantity/{orderId}/{quantity}", 1L, 3))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.memberId", is(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.productDto.productName", is("TestProduct")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.quantity", is(3)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.price", is(30000)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.orderStatus", is("PAYMENT")));
  }

  @Test
  public void testDeleteOrder() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.delete("/order/cancel/{orderId}", 1L))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("1"))
        .andDo(MockMvcResultHandlers.print());
  }
}
