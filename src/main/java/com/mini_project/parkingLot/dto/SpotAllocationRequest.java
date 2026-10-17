package com.mini_project.parkingLot.dto;

import com.mini_project.parkingLot.entity.VehicleType;

public record SpotAllocationRequest(long parkingLotId, VehicleType vehicleType) {
}
