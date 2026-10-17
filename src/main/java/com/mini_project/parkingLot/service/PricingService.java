package com.mini_project.parkingLot.service;

import com.mini_project.parkingLot.dto.PricingRequest;
import com.mini_project.parkingLot.dto.PricingResponse;
import org.springframework.stereotype.Service;

@Service
public interface PricingService {

    public PricingResponse priceTicket(PricingRequest pricingRequest);
}
