package com.shopping.service;

import com.shopping.dto.PaymentRequest;
import com.shopping.dto.PaymentResponse;
import com.shopping.dto.RefundRequest;
import com.shopping.dto.RefundResponse;
import com.shopping.entity.Payment;
import com.shopping.entity.Refund;
import com.shopping.mapper.PaymentMapper;
import com.shopping.mapper.RefundMapper;
import com.shopping.repository.PaymentRepository;
import com.shopping.repository.RefundRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

  @Autowired
  private PaymentRepository paymentRepository;

  @Autowired
  private RefundRepository refundRepository;

  public PaymentResponse processPayment(PaymentRequest request) {
    Payment payment = PaymentMapper.toEntity(request);
    payment = paymentRepository.save(payment);
    return PaymentMapper.toDto(payment);
  }

  public Optional<PaymentResponse> getPaymentByTransactionId(String transactionId) {
    return paymentRepository.findByTransactionId(transactionId)
        .map(PaymentMapper::toDto);
  }

  public RefundResponse processRefund(RefundRequest request) {
    Refund refund = RefundMapper.toEntity(request);
    refund = refundRepository.save(refund);
    return RefundMapper.toDto(refund);
  }
}

