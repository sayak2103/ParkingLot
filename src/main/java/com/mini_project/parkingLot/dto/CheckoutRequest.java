package com.mini_project.parkingLot.dto;

import com.mini_project.parkingLot.entity.ParkingTicket;

public record CheckoutRequest(String ticketUUID) {
}
