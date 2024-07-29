package com.shopping.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDto {

  private String paymentMethod;
  private double amount;
  private String transactionId;
}
