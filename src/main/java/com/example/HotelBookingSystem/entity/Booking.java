package com.example.HotelBookingSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Builder
@Table(name ="bookings")
public class Booking {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String customerName;
    private int pax;
    private String mobile;

    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    @Lob
    @Column(name = "ROOMS_JSON")
    private String roomsJson;


    private double advance;
    private String advanceMode;
    private double kitchenRent;
    private double discount;

    @Column(name = "GST")
    private Integer gst; // 0 or 1


    private double gstAmount;

    private double totalAmount;
    private double balanceAmount;

    private String remarks;
    private String status;

    @Column(name = "paymentCompleted")
    private Integer paymentCompleted;

    private LocalDateTime createdAt;
}
