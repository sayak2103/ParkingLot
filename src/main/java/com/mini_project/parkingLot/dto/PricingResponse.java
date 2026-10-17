package com.mini_project.parkingLot.dto;

import com.mini_project.parkingLot.entity.ParkingTicket;

import java.time.Instant;

public record PricingResponse(ParkingTicket ticket,Instant exitTimestamp, double amount) {
}
