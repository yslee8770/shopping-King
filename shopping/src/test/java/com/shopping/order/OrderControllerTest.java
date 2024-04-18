package com.shopping.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.common.OrderStatus;
import com.shopping.order.dto.OrderDto;
import com.shopping.product.dto.ProductDto;

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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    	 ProductDto productDto = ProductDto.builder()
                 .productName("TestProduct")
                 .price(10000)
                 .build();

		testOrder = OrderDto.builder()
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

        mockMvc.perform(MockMvcRequestBuilders.post("/order/add")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(objectMapper.writeValueAsString(orderList)))
               .andExpect(MockMvcResultMatchers.status().isOk())
//               .andExpect(MockMvcResultMatchers.content().json("[]"))
               .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testFindOrderList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/order/list/{memberId}", testOrder.getMemberId()))
               .andExpect(MockMvcResultMatchers.status().isOk())
//               .andExpect(MockMvcResultMatchers.content().json("[]"))
               .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testFindOrderDetail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/order/detail/{orderId}", 1L))
               .andExpect(MockMvcResultMatchers.status().isOk())
//               .andExpect(MockMvcResultMatchers.jsonPath("$.memberId").value(testOrder.getMemberId()))
//               .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value(testOrder.getQuantity()))
//               .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(testOrder.getPrice()))
//               .andExpect(MockMvcResultMatchers.jsonPath("$.orderStatus").value(testOrder.getOrderStatus().toString()))
               .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testChangeOrderStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/order/changeOrderStatus/{orderId}/{orderStatus}", 1L, OrderStatus.SHIPPING))
               .andExpect(MockMvcResultMatchers.status().isOk())
//               .andExpect(MockMvcResultMatchers.jsonPath("$.orderId").value(1L))
//               .andExpect(MockMvcResultMatchers.jsonPath("$.orderStatus").value(OrderStatus.SHIPPING.toString()))
               .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testChangeOrderQuantity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/order/changeQuantity/{orderId}/{quantity}", 1L, 3))
               .andExpect(MockMvcResultMatchers.status().isOk())
//               .andExpect(MockMvcResultMatchers.jsonPath("$.orderId").value(1L))
//               .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value(3))
               .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testDeleteOrder() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/order/cancel/{orderId}", 1L))
               .andExpect(MockMvcResultMatchers.status().isNoContent())
               .andDo(MockMvcResultHandlers.print());
    }
}
