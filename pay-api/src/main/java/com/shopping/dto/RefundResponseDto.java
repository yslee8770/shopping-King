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
public class RefundResponseDto {

  private Long id;
  private Long paymentId;
  private double amount;
  private String status;
  private LocalDateTime refundDate;
}

