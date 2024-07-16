package com.shopping.mapper;

import com.shopping.dto.RefundRequest;
import com.shopping.dto.RefundResponse;
import com.shopping.entity.Refund;
import java.time.LocalDateTime;

public class RefundMapper {
  public static Refund toEntity(RefundRequest request) {
    return Refund.builder()
        .paymentId(request.getPaymentId())
        .amount(request.getAmount())
        .status("COMPLETED")
        .refundDate(LocalDateTime.now())
        .build();
  }

  public static RefundResponse toDto(Refund refund) {
    return RefundResponse.builder()
        .id(refund.getId())
        .paymentId(refund.getPaymentId())
        .amount(refund.getAmount())
        .status(refund.getStatus())
        .refundDate(refund.getRefundDate())
        .build();
  }
}
