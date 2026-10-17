package com.mini_project.parkingLot.dto;

import com.mini_project.parkingLot.entity.ParkingTicket;
import com.mini_project.parkingLot.entity.SpotType;

import java.time.Instant;

public record TicketResponse(String uuid, long parkingLotId, long floorNumber , long spotNumber,
                             SpotType spotType , String vehicleNumber, Instant entryTime) {
    public static TicketResponse from(ParkingTicket ticket){
        return new TicketResponse(ticket.getId(), ticket.getFloorSpot().getFloor().getParkingLot().getId(),
                ticket.getFloorSpot().getFloor().getFloorNumber(), ticket.getFloorSpot().getSpotNumber(),
                ticket.getFloorSpot().getSpotType(), ticket.getVehicle().getVehicleNumber(), ticket.getEntryTimeStamp());
    }
}
