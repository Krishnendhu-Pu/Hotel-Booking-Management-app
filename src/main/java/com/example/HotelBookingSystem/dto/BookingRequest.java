package com.example.HotelBookingSystem.dto;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingRequest {

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

    private Boolean gst;
    private double gstAmount;
    private double totalAmount;
    private double balanceAmount;
    private Boolean paymentCompleted;
    private String remarks;
    private String status;
}
