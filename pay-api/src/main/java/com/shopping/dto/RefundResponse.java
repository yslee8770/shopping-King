package com.shopping.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RefundResponse {

  private Long id;
  private Long paymentId;
  private double amount;
  private String status;
  private LocalDateTime refundDate;
}

