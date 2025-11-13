package com.example.HotelBookingSystem.dto;

import jakarta.validation.constraints.NotEmpty;

public record RegistrationDTO(
        @NotEmpty String username,
        @NotEmpty String password,
        @NotEmpty String email
) {
}
