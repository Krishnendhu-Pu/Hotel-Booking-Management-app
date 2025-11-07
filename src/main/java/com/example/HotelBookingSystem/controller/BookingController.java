package com.example.HotelBookingSystem.controller;


import com.example.HotelBookingSystem.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app/bookings")
public class BookingController {

    private final BookingService bookingService;

}
