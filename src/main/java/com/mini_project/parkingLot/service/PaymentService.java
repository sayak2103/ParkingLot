package com.mini_project.parkingLot.service;

import com.mini_project.parkingLot.dto.PaymentRequest;
import com.mini_project.parkingLot.dto.PaymentResponse;
import com.mini_project.parkingLot.entity.Payment;
import com.mini_project.parkingLot.entity.PaymentMode;
import com.mini_project.parkingLot.entity.PaymentStatus;
import com.mini_project.parkingLot.entity.PricingMethod;
import com.mini_project.parkingLot.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentResponse getPayment(PaymentRequest paymentRequest) throws InterruptedException {
        Thread.sleep(3000);
        Payment payment = new Payment(paymentRequest.amount(), PaymentStatus.COMPLETE, PaymentMode.UPI, PricingMethod.FIXEDRATE);
        Payment saved_payment = paymentRepository.save(payment);
        return new PaymentResponse(saved_payment.getId(), paymentRequest.amount(), PaymentStatus.COMPLETE);
    }
}
