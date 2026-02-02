package com.example.HotelBookingSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookingResponse {

    private int id;

    private String customerName;
    private int pax;
    private String mobile;

    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    private List<RoomDTO> rooms;

    private double advance;
    private String advanceMode;
    private double kitchenRent;
    private double discount;

    private boolean gst;
    private boolean paymentCompleted;
    private double gstAmount;

    private double totalAmount;
    private double balanceAmount;

    private String remarks;
    private String status;

    private LocalDateTime createdAt;
}
