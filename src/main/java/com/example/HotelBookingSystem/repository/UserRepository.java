package com.example.HotelBookingSystem.repository;

import com.example.HotelBookingSystem.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);
}
