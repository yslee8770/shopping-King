package com.shopping.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDto {

  private Long id;
  private String paymentMethod;
  private double amount;
  private String status;
  private LocalDateTime paymentDate;
  private String transactionId;
}

