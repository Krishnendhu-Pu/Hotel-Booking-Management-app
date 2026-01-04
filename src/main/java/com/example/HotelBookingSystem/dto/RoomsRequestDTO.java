package com.example.HotelBookingSystem.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RoomsRequestDTO(@NotEmpty String roomType,
                              @NotNull int rate,
                              Double gstPercent,
                              Double acRate,
                              Double extraBedFee,
                              String remarks,
                              boolean ac,
                              @NotNull int noOfRooms) {
}
