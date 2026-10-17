package com.mini_project.parkingLot.repository;

import com.mini_project.parkingLot.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {

}
