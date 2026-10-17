package com.mini_project.parkingLot.service;

import com.mini_project.parkingLot.entity.*;
import com.mini_project.parkingLot.exception.InvalidParkingLotException;
import com.mini_project.parkingLot.dto.TicketRequest;
import com.mini_project.parkingLot.dto.TicketResponse;
import com.mini_project.parkingLot.exception.NoAvailableParkingSpotException;
import com.mini_project.parkingLot.exception.ResourceNotFoundException;
import com.mini_project.parkingLot.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TicketingService {
    private final SpotAllocationService spotAllocationService;
    private final ParkingTicketRepository parkingTicketRepository;
    private final FloorSpotRepository floorSpotRepository;
    private final ParkingLotRepository parkingLotRepository;
    private final VehicleRepository vehicleRepository;
    private final FloorRepository floorRepository;

    public TicketingService(SpotAllocationService spotAllocationService,FloorRepository floorRepository, ParkingTicketRepository parkingTicketRepository, FloorSpotRepository floorSpotRepository, ParkingLotRepository parkingLotRepository, VehicleRepository vehicleRepository) {
        this.spotAllocationService = spotAllocationService;
        this.parkingTicketRepository = parkingTicketRepository;
        this.floorSpotRepository = floorSpotRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.vehicleRepository = vehicleRepository;
        this.floorRepository = floorRepository;
    }

    @Transactional
    public TicketResponse getParkingTicket(TicketRequest ticketRequest){

        ParkingLot parkingLot = parkingLotRepository.findById(
                ticketRequest.parkingLotId()
        ).orElseThrow(
                () -> new ResourceNotFoundException("parking lot not found")
        );

        if(!vehicleRepository.existsByVehicleNumber(ticketRequest.vehicleNumber())){
            vehicleRepository.save(new Vehicle(
                    ticketRequest.vehicleNumber(),
                    ticketRequest.vehicleType()
            ));
        }
        Vehicle vehicle = vehicleRepository.findByVehicleNumber(ticketRequest.vehicleNumber())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Vehicle not found with number : " + ticketRequest.vehicleNumber())
                );

        // get floor spot
        FloorSpot floorSpot = spotAllocationService.allocateSpot(parkingLot, vehicle)
                .orElseThrow(
                        () -> new ResourceNotFoundException("No Space for you to park")
                );

        // mark the floor spot as booked
        floorSpot.setSpotStatus(SpotStatus.OCCUPIED);
        floorSpotRepository.save(floorSpot);

        // change the value of the floor
        Floor floor = floorRepository.findByIdForUpdate(floorSpot.getFloor().getId())
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Floor not found with floorId : " + floorSpot.getFloor().getId())
                        );
        floor.decrementAvailable();

        // generate parking ticket , save it
        ParkingTicket ticket = new ParkingTicket(floorSpot, vehicle, null, -1, Instant.now(), null, TicketStatus.PARKED);
        //save parking ticket
        parkingTicketRepository.save(ticket);

        // send parking response
        return TicketResponse.from(ticket);

    }
}
