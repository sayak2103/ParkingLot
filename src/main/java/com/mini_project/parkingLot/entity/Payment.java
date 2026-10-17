package com.mini_project.parkingLot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "payments", uniqueConstraints = {
        @UniqueConstraint(name = "uk_invoice", columnNames = "invoiceId")
})
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;

    private double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @Enumerated(EnumType.STRING)
    private PricingMethod pricingMethod;

    Payment(){

    }

    public Payment(double amount, PaymentStatus paymentStatus, PaymentMode paymentMode, PricingMethod pricingMethod) {
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.paymentMode = paymentMode;
        this.pricingMethod = pricingMethod;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public PricingMethod getPricingMethod() {
        return pricingMethod;
    }

    public void setPricingMethod(PricingMethod pricingMethod) {
        this.pricingMethod = pricingMethod;
    }
}
