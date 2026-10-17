package com.mini_project.parkingLot.service.impl;

import com.mini_project.parkingLot.dto.PricingRequest;
import com.mini_project.parkingLot.dto.PricingResponse;
import com.mini_project.parkingLot.entity.ParkingTicket;
import com.mini_project.parkingLot.entity.SpotType;
import com.mini_project.parkingLot.entity.VehicleType;
import com.mini_project.parkingLot.service.PricingService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class FixedPricingService implements PricingService {
    private static final double COMPACT_RATE = 10;
    private static final double MEDIUM_RATE = 15;
    private static final double LARGE_RATE = 30;
    // rate units in hours


    @Override
    public PricingResponse priceTicket(PricingRequest pricingRequest) {
        ParkingTicket ticket = pricingRequest.ticket();
        Instant exitTimeStamp = Instant.now();
        double hours = Duration.between(ticket.getEntryTimeStamp(), exitTimeStamp).toSeconds()/(3600.0D);
        //
        SpotType spotType = ticket.getFloorSpot().getSpotType();
        double amount = 0;
        if(spotType==SpotType.COMPACT){
            amount = COMPACT_RATE * hours;
        } else if (spotType==SpotType.MEDIUM) {
            amount = MEDIUM_RATE *hours;
        } else if (spotType == SpotType.LARGE){
            amount = LARGE_RATE * hours;
        }
        amount = amount*1.18; // GST FOR MAUSI
        return new PricingResponse(ticket, exitTimeStamp, amount);
    }
}
