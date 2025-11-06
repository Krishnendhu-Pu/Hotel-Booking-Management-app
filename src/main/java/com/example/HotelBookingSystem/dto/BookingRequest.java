package com.example.HotelBookingSystem.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingRequest {

    private String customerName;
    private String roomType;
    private LocalDateTime bookingDate;
    private String status;
}
