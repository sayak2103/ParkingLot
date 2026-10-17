package com.mini_project.parkingLot.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "parkingLots")
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "parkingLot")
    private Set<Floor> floorSet = new HashSet<Floor>();

    ParkingLot(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Floor> getFloorSet() {
        return floorSet;
    }

    public void setFloorSet(Set<Floor> floorSet) {
        this.floorSet = floorSet;
    }
}
