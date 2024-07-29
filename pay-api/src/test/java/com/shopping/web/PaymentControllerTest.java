package com.shopping.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.shopping.dto.PaymentRequestDto;
import com.shopping.dto.PaymentResponseDto;
import com.shopping.dto.RefundRequestDto;
import com.shopping.dto.RefundResponseDto;
import com.shopping.service.PaymentService;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PaymentService paymentService;

  @Test
  public void testProcessPayment() throws Exception {
    PaymentResponseDto paymentResponse = PaymentResponseDto.builder()
        .id(1L)
        .paymentMethod("Credit Card")
        .amount(100.0)
        .status("COMPLETED")
        .paymentDate(LocalDateTime.now())
        .transactionId("txn_123456")
        .build();

    when(paymentService.processPayment(any(PaymentRequestDto.class))).thenReturn(paymentResponse);

    String paymentRequestJson = "{ \"paymentMethod\": \"Credit Card\", \"amount\": 100.0, \"transactionId\": \"txn_123456\" }";

    mockMvc.perform(post("/payments/process")
            .contentType(MediaType.APPLICATION_JSON)
            .content(paymentRequestJson))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.paymentMethod").value("Credit Card"))
        .andExpect(jsonPath("$.amount").value(100.0))
        .andExpect(jsonPath("$.status").value("COMPLETED"));
  }

  @Test
  public void testGetPaymentByTransactionId() throws Exception {
    PaymentResponseDto paymentResponse = PaymentResponseDto.builder()
        .id(1L)
        .paymentMethod("PayPal")
        .amount(200.0)
        .status("COMPLETED")
        .paymentDate(LocalDateTime.now())
        .transactionId("txn_654321")
        .build();

    when(paymentService.getPaymentByTransactionId("txn_654321")).thenReturn(Optional.of(paymentResponse));

    mockMvc.perform(get("/payments/txn_654321"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.paymentMethod").value("PayPal"))
        .andExpect(jsonPath("$.amount").value(200.0));
  }

  @Test
  public void testProcessRefund() throws Exception {
    RefundResponseDto refundResponse = RefundResponseDto.builder()
        .id(1L)
        .paymentId(1L)
        .amount(300.0)
        .status("COMPLETED")
        .refundDate(LocalDateTime.now())
        .build();

    when(paymentService.processRefund(any(RefundRequestDto.class))).thenReturn(refundResponse);

    String refundRequestJson = "{ \"paymentId\": 1, \"amount\": 300.0 }";

    mockMvc.perform(post("/payments/refund")
            .contentType(MediaType.APPLICATION_JSON)
            .content(refundRequestJson))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.paymentId").value(1))
        .andExpect(jsonPath("$.amount").value(300.0))
        .andExpect(jsonPath("$.status").value("COMPLETED"));
  }
}