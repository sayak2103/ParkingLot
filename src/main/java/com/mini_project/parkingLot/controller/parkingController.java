package com.mini_project.parkingLot.controller;

import com.mini_project.parkingLot.dto.TicketRequest;
import com.mini_project.parkingLot.dto.TicketResponse;
import com.mini_project.parkingLot.service.TicketingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/park")
public class parkingController {
    private final TicketingService ticketingService;

    public parkingController(TicketingService ticketingService) {
        this.ticketingService = ticketingService;
    }

    @GetMapping("")
    public TicketResponse getParkingTicket(@RequestBody TicketRequest ticketRequest){
        return ticketingService.getParkingTicket(ticketRequest);
    }
}
