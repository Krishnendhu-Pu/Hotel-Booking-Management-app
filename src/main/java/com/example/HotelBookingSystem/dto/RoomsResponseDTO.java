package com.example.HotelBookingSystem.dto;

public record RoomsResponseDTO(int id,
                               String roomType,
                               int rate,
                               Integer gstPercent,    // optional Integer supports null
                               Integer acRate,        // optional
                               Integer extraBedFee,   // optional
                               String remarks,        // optional
                               int noOfRooms) {
}
