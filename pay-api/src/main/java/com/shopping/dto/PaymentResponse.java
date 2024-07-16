package com.shopping.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentResponse {

  private Long id;
  private String paymentMethod;
  private double amount;
  private String status;
  private LocalDateTime paymentDate;
  private String transactionId;
}

