package com.shopping.web;

import com.shopping.dto.PaymentRequestDto;
import com.shopping.dto.PaymentResponseDto;
import com.shopping.dto.RefundRequestDto;
import com.shopping.dto.RefundResponseDto;
import com.shopping.service.PaymentService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

  @Autowired
  private PaymentService paymentService;

  @PostMapping("/process")
  public ResponseEntity<PaymentResponseDto> processPayment(@RequestBody PaymentRequestDto request) {
    PaymentResponseDto response = paymentService.processPayment(request);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{transactionId}")
  public ResponseEntity<PaymentResponseDto> getPayment(@PathVariable String transactionId) {
    Optional<PaymentResponseDto> response = paymentService.getPaymentByTransactionId(transactionId);
    return response.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping("/refund")
  public ResponseEntity<RefundResponseDto> processRefund(@RequestBody RefundRequestDto request) {
    RefundResponseDto response = paymentService.processRefund(request);
    return ResponseEntity.ok(response);
  }
}

