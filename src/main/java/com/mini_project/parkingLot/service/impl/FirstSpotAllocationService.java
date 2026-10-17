package com.mini_project.parkingLot.service.impl;

import com.mini_project.parkingLot.entity.FloorSpot;
import com.mini_project.parkingLot.entity.ParkingLot;
import com.mini_project.parkingLot.entity.Vehicle;
import com.mini_project.parkingLot.entity.VehicleType;
import com.mini_project.parkingLot.repository.FloorSpotRepository;
import com.mini_project.parkingLot.service.SpotAllocationService;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FirstSpotAllocationService implements SpotAllocationService {

    private final FloorSpotRepository floorSpotRepository;

    public FirstSpotAllocationService(FloorSpotRepository floorSpotRepository) {
        this.floorSpotRepository = floorSpotRepository;
    }

    @Override
    public Optional<FloorSpot> allocateSpot(@NotNull ParkingLot parkingLot, @NotNull Vehicle vehicle) {
        Optional<FloorSpot> floorSpot;
        if(vehicle.getVehicleType()== VehicleType.MOTOR_CYCLE){
            floorSpot = floorSpotRepository.firstSpotAllocationCompact(parkingLot.getId());
        } else if (vehicle.getVehicleType()== VehicleType.CAR) {
            floorSpot = floorSpotRepository.firstSpotAllocationMedium(parkingLot.getId());
        } else {
            floorSpot = floorSpotRepository.firstSpotAllocationLarge(parkingLot.getId());
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return floorSpot;
    }
}
