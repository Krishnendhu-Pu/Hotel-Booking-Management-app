package com.example.HotelBookingSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookingResponse {

    private int id;
    private String customerName;
    private String roomType;
    private LocalDateTime bookingDate;
    private String status;
}
