package com.example.HotelBookingSystem.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RoomsRequestDTO(@NotEmpty String roomType,
                              @NotNull int rate,
                              Integer gstPercent,
                              Integer acRate,
                              Integer extraBedFee,
                              String remarks,
                              @NotNull int noOfRooms) {
}
