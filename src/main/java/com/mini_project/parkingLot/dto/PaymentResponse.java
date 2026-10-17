package com.mini_project.parkingLot.dto;

import com.mini_project.parkingLot.entity.PaymentStatus;

public record PaymentResponse(String InvoiceId, double amount, PaymentStatus paymentStatus) {
}
