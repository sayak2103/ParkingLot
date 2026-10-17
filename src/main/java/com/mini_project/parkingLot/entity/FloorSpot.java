package com.mini_project.parkingLot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "floorSpots")
public class FloorSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long spotNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floorId", nullable = false)
    private Floor floor;

    @Enumerated(EnumType.STRING)
    private SpotType spotType;

    @Enumerated(EnumType.STRING)
    private SpotStatus spotStatus;

    private int distanceFromEntrance;

    FloorSpot(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public void setSpotType(SpotType spotType) {
        this.spotType = spotType;
    }

    public SpotStatus getSpotStatus() {
        return spotStatus;
    }

    public void setSpotStatus(SpotStatus spotStatus) {
        this.spotStatus = spotStatus;
    }

    public int getDistanceFromEntrance() {
        return distanceFromEntrance;
    }

    public void setDistanceFromEntrance(int distanceFromEntrance) {
        this.distanceFromEntrance = distanceFromEntrance;
    }

    public long getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(long spotNumber) {
        this.spotNumber = spotNumber;
    }

    public void reserve() {
        this.spotStatus = SpotStatus.OCCUPIED;
    }

    public void release(){
        this.spotStatus = SpotStatus.AVAILABLE;
    }
}
