package com.example.HotelBookingSystem.dto;

import jakarta.validation.constraints.NotEmpty;

public record RoomsRequestDTO(@NotEmpty String roomType,
                              int rate,
                              String remarks,
                              int noOfRooms) {
}
