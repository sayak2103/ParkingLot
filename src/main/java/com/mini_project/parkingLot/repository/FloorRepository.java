package com.mini_project.parkingLot.repository;

import com.mini_project.parkingLot.entity.Floor;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.Optional;

public interface FloorRepository extends JpaRepository<Floor, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({
            @QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")
    })
    @Query(value = "SELECT f FROM Floor f WHERE f.Id = :id")
    Optional<Floor> findByIdForUpdate(long id);
}
