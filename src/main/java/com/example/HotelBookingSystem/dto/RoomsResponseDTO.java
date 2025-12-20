package com.example.HotelBookingSystem.dto;

public record RoomsResponseDTO(int id,
                               String roomType,
                               int rate,
                               String remarks,
                               int noOfRooms) {
}
