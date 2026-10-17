package com.mini_project.parkingLot.exception;

public class NoAvailableParkingSpotException extends RuntimeException {
    public NoAvailableParkingSpotException(String s) {
        super(s);
    }
}
