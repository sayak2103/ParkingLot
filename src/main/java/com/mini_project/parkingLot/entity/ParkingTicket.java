package com.mini_project.parkingLot.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "parkingTickets")
public class ParkingTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "floorSpotId", nullable = false)
    private FloorSpot floorSpot;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicleId", nullable = false)
    private Vehicle vehicle;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paymentId", nullable = true)
    private Payment payment;

    private double amount;
    private Instant entryTimeStamp;
    private Instant exitTimeStamp;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    ParkingTicket(){

    }

    public ParkingTicket(FloorSpot floorSpot, Vehicle vehicle, Payment payment, double amount, Instant entryTimeStamp, Instant exitTimeStamp, TicketStatus ticketStatus) {
        this.floorSpot = floorSpot;
        this.vehicle = vehicle;
        this.payment = payment;
        this.amount = amount;
        this.entryTimeStamp = entryTimeStamp;
        this.exitTimeStamp = exitTimeStamp;
        this.ticketStatus = ticketStatus;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public FloorSpot getFloorSpot() {
        return floorSpot;
    }

    public void setFloorSpot(FloorSpot floorSpot) {
        this.floorSpot = floorSpot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Instant getEntryTimeStamp() {
        return entryTimeStamp;
    }

    public void setEntryTimeStamp(Instant entryTimeStamp) {
        this.entryTimeStamp = entryTimeStamp;
    }

    public Instant getExitTimeStamp() {
        return exitTimeStamp;
    }

    public void setExitTimeStamp(Instant exitTimeStamp) {
        this.exitTimeStamp = exitTimeStamp;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
