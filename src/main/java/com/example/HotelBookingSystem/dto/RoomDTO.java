package com.example.HotelBookingSystem.dto;

public record RoomDTO(String roomType,
                      int noOfRooms,
                      int extraBed,
                      boolean ac,
                      double rate,
                      double gstPercent,
                      double acRate,
                      double extraBedFee
) {
}
