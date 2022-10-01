package com.decagon.chompapp.controllers;

import com.decagon.chompapp.dtos.OrderDto;
import com.decagon.chompapp.dtos.OrderResponseDto;
import com.decagon.chompapp.services.CheckoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
@CrossOrigin("*")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }


    @PostMapping
    public ResponseEntity<OrderResponseDto> checkout(@RequestBody OrderDto orderDto) {
        return checkoutService.checkout(orderDto);
    }
}
