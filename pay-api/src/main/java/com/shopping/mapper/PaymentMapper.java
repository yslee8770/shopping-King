package com.shopping.mapper;

import com.shopping.dto.PaymentRequest;
import com.shopping.dto.PaymentResponse;
import com.shopping.entity.Payment;
import java.time.LocalDateTime;

public class PaymentMapper {
  public static Payment toEntity(PaymentRequest request) {
    return Payment.builder()
        .paymentMethod(request.getPaymentMethod())
        .amount(request.getAmount())
        .status("COMPLETED")
        .paymentDate(LocalDateTime.now())
        .transactionId(request.getTransactionId())
        .build();
  }

  public static PaymentResponse toDto(Payment payment) {
    return PaymentResponse.builder()
        .id(payment.getId())
        .paymentMethod(payment.getPaymentMethod())
        .amount(payment.getAmount())
        .status(payment.getStatus())
        .paymentDate(payment.getPaymentDate())
        .transactionId(payment.getTransactionId())
        .build();
  }
}
