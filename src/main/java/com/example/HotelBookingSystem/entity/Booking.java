package com.example.HotelBookingSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
@Table(name ="bookings")
public class Booking {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String customerName;
    private String roomType;
    private LocalDateTime bookingDate;
    private String status;
}
