package com.mini_project.parkingLot.dto;

import com.mini_project.parkingLot.entity.ParkingTicket;
import com.mini_project.parkingLot.entity.SpotType;

import java.time.Instant;

public record CheckoutResponse(String uuid, long floorNumber , long spotNumber,
                             SpotType spotType , String vehicleNumber, Instant entryTime,
                               Instant exitTime, double amount) {
    public static CheckoutResponse from(ParkingTicket ticket){
        return new CheckoutResponse(ticket.getId(), ticket.getFloorSpot().getFloor().getFloorNumber(),
                ticket.getFloorSpot().getSpotNumber(), ticket.getFloorSpot().getSpotType(),
                ticket.getVehicle().getVehicleNumber(), ticket.getEntryTimeStamp(),
                ticket.getExitTimeStamp(), ticket.getAmount());
    }
}
