package com.mini_project.parkingLot.repository;

import com.mini_project.parkingLot.entity.ParkingTicket;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({
            @QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")
    })
    @Query(value = "SELECT pt FROM ParkingTicket pt WHERE pt.Id = :UUID AND pt.ticketStatus = 'PARKED' ")
    Optional<ParkingTicket> findByIdAndTicketStatusParkedForUpdate(String UUID);
}
