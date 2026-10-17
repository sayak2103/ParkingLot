package com.mini_project.parkingLot.controller;

import com.mini_project.parkingLot.dto.CheckoutRequest;
import com.mini_project.parkingLot.dto.CheckoutResponse;
import com.mini_project.parkingLot.service.CheckoutService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkout")
public class checkoutController {
    private final CheckoutService checkoutService;

    public checkoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("")
    public CheckoutResponse checkout(@RequestBody CheckoutRequest checkoutRequest) throws InterruptedException {
        return checkoutService.processTicket(checkoutRequest);
    }
}
