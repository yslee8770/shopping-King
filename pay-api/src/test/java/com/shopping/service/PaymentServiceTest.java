package com.shopping.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.shopping.dto.PaymentRequestDto;
import com.shopping.dto.PaymentResponseDto;
import com.shopping.dto.RefundRequestDto;
import com.shopping.dto.RefundResponseDto;
import com.shopping.entity.Payment;
import com.shopping.entity.Refund;
import com.shopping.mapper.PaymentMapper;
import com.shopping.mapper.RefundMapper;
import com.shopping.repository.PaymentRepository;
import com.shopping.repository.RefundRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
  @InjectMocks
  private PaymentService paymentService;

  @Mock
  private PaymentRepository paymentRepository;

  @Mock
  private RefundRepository refundRepository;


  @Test
  @DisplayName("결제 테스트")
  public void testProcessPayment() {
    PaymentRequestDto request = new PaymentRequestDto("CREDIT_CARD", 100.0, "TX123");
    Payment payment = PaymentMapper.toEntity(request);
    when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

    PaymentResponseDto response = paymentService.processPayment(request);

    assertNotNull(response);
    assertEquals("CREDIT_CARD", response.getPaymentMethod());
    assertEquals(100.0, response.getAmount());
    assertEquals("COMPLETED", response.getStatus());
  }
  @Test
  public void testGetPaymentByTransactionId() {
    String transactionId = "TX123";
    Payment payment = Payment.builder().transactionId(transactionId).build();
    when(paymentRepository.findByTransactionId(transactionId)).thenReturn(Optional.of(payment));

    Optional<PaymentResponseDto> response = paymentService.getPaymentByTransactionId(transactionId);

    assertTrue(response.isPresent());
    assertEquals(transactionId, response.get().getTransactionId());
  }
  @Test
  public void testProcessRefund() {
    RefundRequestDto request = new RefundRequestDto(1L, 50.0);
    Refund refund = RefundMapper.toEntity(request);
    when(refundRepository.save(any(Refund.class))).thenReturn(refund);

    RefundResponseDto response = paymentService.processRefund(request);

    assertNotNull(response);
    assertEquals(50.0, response.getAmount());
    assertEquals("COMPLETED", response.getStatus());
  }
}