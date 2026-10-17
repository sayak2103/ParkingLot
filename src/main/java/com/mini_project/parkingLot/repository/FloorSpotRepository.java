package com.mini_project.parkingLot.repository;

import com.mini_project.parkingLot.entity.FloorSpot;
import com.mini_project.parkingLot.entity.VehicleType;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FloorSpotRepository extends JpaRepository<FloorSpot, Long> {

    @Query(value = "select fs.* from floor_spots fs " +
            "join floors f on fs.floor_id = f.id "  +
            "join parking_lots pl on f.parking_lot_id = pl.id " +
            "where pl.id = :parkingLotId " +
            "and fs.spot_status = 'AVAILABLE' " +
            "order by f.floor_number, fs.distance_from_entrance " +
            "limit 1 for update skip locked ",
            nativeQuery = true
    )
    Optional<FloorSpot> firstSpotAllocationCompact(@Param("parkingLotId") long parkingLotId);



    @Query(value = "select fs.* from floor_spots fs " +
            "join floors f on fs.floor_id = f.id "  +
            "join parking_lots pl on f.parking_lot_id = pl.id " +
            "where pl.id = :parkingLotId " +
            "and fs.spot_status = 'AVAILABLE' " +
            "and fs.spot_type in ('MEDIUM' , 'LARGE') " +
            "order by f.floor_number ASC, fs.distance_from_entrance ASC " +
            "limit 1 for update skip locked ",
            nativeQuery = true
    )
    Optional<FloorSpot> firstSpotAllocationMedium(@Param("parkingLotId") long parkingLotId);


    @Query(value = "select fs.* from floor_spots fs " +
            "join floors f on fs.floor_id = f.id "  +
            "join parking_lots pl on f.parking_lot_id = pl.id " +
            "where pl.id = :parkingLotId " +
            "and fs.spot_status = 'AVAILABLE' " +
            "and fs.spot_type IN ('LARGE') " +
            "order by f.floor_number, fs.distance_from_entrance " +
            "limit 1 for update skip locked ",
            nativeQuery = true
    )
    Optional<FloorSpot> firstSpotAllocationLarge(@Param("parkingLotId") long parkingLotId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({
            @QueryHint(name = "jakarta.persistence.lock.timeout", value = "500")
    })
    @Query(value = "SELECT fs FROM FloorSpot fs WHERE fs.Id = :Id")
    Optional<FloorSpot> findByIdForUpdate(long Id);

}
