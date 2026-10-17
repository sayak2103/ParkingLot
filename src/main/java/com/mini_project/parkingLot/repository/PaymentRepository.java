package com.mini_project.parkingLot.repository;

import com.mini_project.parkingLot.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}
