package com.shopping.web;

import com.shopping.dto.PaymentRequest;
import com.shopping.dto.PaymentResponse;
import com.shopping.dto.RefundRequest;
import com.shopping.dto.RefundResponse;
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
  public ResponseEntity<PaymentResponse> processPayment(@RequestBody PaymentRequest request) {
    PaymentResponse response = paymentService.processPayment(request);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{transactionId}")
  public ResponseEntity<PaymentResponse> getPayment(@PathVariable String transactionId) {
    Optional<PaymentResponse> response = paymentService.getPaymentByTransactionId(transactionId);
    return response.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping("/refund")
  public ResponseEntity<RefundResponse> processRefund(@RequestBody RefundRequest request) {
    RefundResponse response = paymentService.processRefund(request);
    return ResponseEntity.ok(response);
  }
}

