package com.shopping.dto;


import lombok.Getter;

@Getter
public class PaymentRequest {

  private String paymentMethod;
  private double amount;
  private String transactionId;
}
