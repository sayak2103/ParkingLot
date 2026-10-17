package com.mini_project.parkingLot.dto;

import com.mini_project.parkingLot.entity.VehicleType;

public record TicketRequest(Long parkingLotId, String vehicleNumber, VehicleType vehicleType) {
}
