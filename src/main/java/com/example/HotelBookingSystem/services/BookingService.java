package com.example.HotelBookingSystem.services;

import com.example.HotelBookingSystem.dto.BookingRequest;
import com.example.HotelBookingSystem.dto.BookingResponse;
import com.example.HotelBookingSystem.dto.RoomsRequestDTO;
import com.example.HotelBookingSystem.dto.RoomsResponseDTO;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    BookingResponse createBooking(BookingRequest request);
    List<BookingResponse> getAllBookings();
    BookingResponse getBookingById(int id);
    BookingResponse updateBooking(int id, BookingRequest request);
    void deleteBooking(int id);
    List<BookingResponse> getBookingByDateRange(LocalDate startDate, LocalDate endDate);
    Mono<String> addRoomViaRoomService(RoomsRequestDTO roomsRequestDTO);
    List<RoomsResponseDTO> getRooms();
}
