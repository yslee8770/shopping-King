package com.shopping.mapper;

import com.shopping.dto.PaymentRequestDto;
import com.shopping.dto.PaymentResponseDto;
import com.shopping.entity.Payment;
import java.time.LocalDateTime;

public class PaymentMapper {
  public static Payment toEntity(PaymentRequestDto request) {
    return Payment.builder()
        .paymentMethod(request.getPaymentMethod())
        .amount(request.getAmount())
        .status("COMPLETED")
        .paymentDate(LocalDateTime.now())
        .transactionId(request.getTransactionId())
        .build();
  }

  public static PaymentResponseDto toDto(Payment payment) {
    return PaymentResponseDto.builder()
        .id(payment.getId())
        .paymentMethod(payment.getPaymentMethod())
        .amount(payment.getAmount())
        .status(payment.getStatus())
        .paymentDate(payment.getPaymentDate())
        .transactionId(payment.getTransactionId())
        .build();
  }
}
