package com.mini_project.parkingLot.dto;

import com.mini_project.parkingLot.entity.FloorSpot;
import com.mini_project.parkingLot.entity.VehicleType;

public record SpotAllocationResponse(long parkingLotId, VehicleType vehicleType, FloorSpot spot) {
}
