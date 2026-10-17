package com.mini_project.parkingLot.service;

import com.mini_project.parkingLot.entity.FloorSpot;
import com.mini_project.parkingLot.entity.ParkingLot;
import com.mini_project.parkingLot.entity.Vehicle;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface SpotAllocationService {
    Optional<FloorSpot> allocateSpot(ParkingLot parkingLot, Vehicle vehicle);
}
