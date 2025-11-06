package com.example.HotelBookingSystem.services;

import com.example.HotelBookingSystem.dto.BookingRequest;
import com.example.HotelBookingSystem.dto.BookingResponse;

import java.util.List;

public interface BookingService {
    BookingResponse createBooking(BookingRequest request);
    List<BookingResponse> getAllBookings();
    BookingResponse getBookingById(int id);
    BookingResponse updateBooking(int id, BookingRequest request);
    void deleteBooking();
}
