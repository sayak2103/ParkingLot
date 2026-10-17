package com.mini_project.parkingLot.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "floors")
public class Floor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parkingLotId", nullable = false)
    private ParkingLot parkingLot;

    @OneToMany(mappedBy = "floor")
    private Set<FloorSpot> floorSpotSet = new HashSet<>();

    private int floorNumber;

    private long totalCapacity;
    private long availableCapacity;

    Floor(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public long getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(long totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public long getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(long availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    public Set<FloorSpot> getFloorSpotSet() {
        return floorSpotSet;
    }

    public void setFloorSpotSet(Set<FloorSpot> floorSpotSet) {
        this.floorSpotSet = floorSpotSet;
    }

    public void decrementAvailable(){
        this.availableCapacity--;
    }

    public void incrementAvailable(){
        this.availableCapacity++;
    }
}
