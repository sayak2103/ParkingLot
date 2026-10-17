package com.mini_project.parkingLot.service;

import com.mini_project.parkingLot.dto.*;
import com.mini_project.parkingLot.entity.*;
import com.mini_project.parkingLot.exception.ResourceNotFoundException;
import com.mini_project.parkingLot.repository.FloorRepository;
import com.mini_project.parkingLot.repository.FloorSpotRepository;
import com.mini_project.parkingLot.repository.ParkingTicketRepository;
import com.mini_project.parkingLot.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CheckoutService {
    private final PricingService pricingService;
    private final PaymentService paymentService;
    private final FloorRepository floorRepository;
    private final FloorSpotRepository floorSpotRepository;
    private final ParkingTicketRepository parkingTicketRepository;
    private final PaymentRepository paymentRepository;

    public CheckoutService(PricingService pricingService, PaymentService paymentService, FloorRepository floorRepository, FloorSpotRepository floorSpotRepository, ParkingTicketRepository parkingTicketRepository, PaymentRepository paymentRepository) {
        this.pricingService = pricingService;
        this.paymentService = paymentService;
        this.floorRepository = floorRepository;
        this.floorSpotRepository = floorSpotRepository;
        this.parkingTicketRepository = parkingTicketRepository;
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public CheckoutResponse processTicket(CheckoutRequest checkoutRequest) throws InterruptedException {

        // get the ticket to process
        ParkingTicket ticket = parkingTicketRepository.findByIdAndTicketStatusParkedForUpdate(checkoutRequest.ticketUUID())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Ticket not found to process/ already processed.")
                );

        //
        PricingResponse pricingResponse = pricingService.priceTicket(new PricingRequest(ticket));

        //
        ticket.setExitTimeStamp(pricingResponse.exitTimestamp());
        ticket.setAmount(pricingResponse.amount());

        // make the payment
        PaymentResponse paymentResponse = paymentService.getPayment(new PaymentRequest(pricingResponse.amount()));
        Payment payment = paymentRepository.findById(paymentResponse.InvoiceId())
                        .orElseThrow(() -> new ResourceNotFoundException("payment data could not find"));
        ticket.setPayment(payment);
        //
        long floorSpotId = ticket.getFloorSpot().getId();
        FloorSpot floorSpot = floorSpotRepository.findByIdForUpdate(floorSpotId)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Specified floorSpot not found")
                        );
        floorSpot.setSpotStatus(SpotStatus.AVAILABLE);
        ticket.setTicketStatus(TicketStatus.COMPLETE);
        //
        Floor floor = floorRepository.findByIdForUpdate(floorSpot.getFloor().getId())
                        .orElseThrow(
                                () -> new ResourceNotFoundException("floor not found with id : ")
                        );
        floor.incrementAvailable();
        //
        parkingTicketRepository.save(ticket);
        floorSpotRepository.save(floorSpot);
        return CheckoutResponse.from(ticket);
    }
}
