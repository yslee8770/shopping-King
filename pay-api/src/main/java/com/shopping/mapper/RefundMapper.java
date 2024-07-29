package com.shopping.mapper;

import com.shopping.dto.RefundRequestDto;
import com.shopping.dto.RefundResponseDto;
import com.shopping.entity.Refund;
import java.time.LocalDateTime;

public class RefundMapper {
  public static Refund toEntity(RefundRequestDto request) {
    return Refund.builder()
        .paymentId(request.getPaymentId())
        .amount(request.getAmount())
        .status("COMPLETED")
        .refundDate(LocalDateTime.now())
        .build();
  }

  public static RefundResponseDto toDto(Refund refund) {
    return RefundResponseDto.builder()
        .id(refund.getId())
        .paymentId(refund.getPaymentId())
        .amount(refund.getAmount())
        .status(refund.getStatus())
        .refundDate(refund.getRefundDate())
        .build();
  }
}
