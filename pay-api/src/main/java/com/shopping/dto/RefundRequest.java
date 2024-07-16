package com.shopping.dto;

import lombok.Getter;

@Getter
public class RefundRequest {

  private Long paymentId;
  private double amount;
}

