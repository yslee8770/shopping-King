package com.shopping.service;

import com.shopping.dto.PaymentRequestDto;
import com.shopping.dto.PaymentResponseDto;
import com.shopping.dto.RefundRequestDto;
import com.shopping.dto.RefundResponseDto;
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

  public PaymentResponseDto processPayment(PaymentRequestDto request) {
    Payment payment = PaymentMapper.toEntity(request);
    payment = paymentRepository.save(payment);
    return PaymentMapper.toDto(payment);
  }

  public Optional<PaymentResponseDto> getPaymentByTransactionId(String transactionId) {
    return paymentRepository.findByTransactionId(transactionId)
        .map(PaymentMapper::toDto);
  }

  public RefundResponseDto processRefund(RefundRequestDto request) {
    Refund refund = RefundMapper.toEntity(request);
    refund = refundRepository.save(refund);
    return RefundMapper.toDto(refund);
  }
}

