package com.example.HotelBookingSystem.dto;

public record RoomsResponseDTO(int id,
                               String roomType,
                               int rate,
                               Double gstPercent,    // optional Integer supports null
                               Double acRate,        // optional
                               Double extraBedFee,   // optional
                               String remarks,        // optional
                               int noOfRooms) {
}
