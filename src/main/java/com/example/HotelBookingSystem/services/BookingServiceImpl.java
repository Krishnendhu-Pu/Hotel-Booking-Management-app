package com.example.HotelBookingSystem.services;

import com.example.HotelBookingSystem.dto.BookingRequest;
import com.example.HotelBookingSystem.dto.BookingResponse;
import com.example.HotelBookingSystem.entity.Booking;
import com.example.HotelBookingSystem.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public BookingResponse createBooking(BookingRequest request){
       Booking booking = Booking.builder()
               .customerName(request.getCustomerName())
               .roomType(request.getRoomType())
               .bookingDate(request.getBookingDate())
               .status(request.getStatus())
               .build();
       Booking saved = bookingRepository.save(booking);
       return mapToResponse(saved);

    }

    public List<BookingResponse> getAllBookings(){
        return bookingRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

   public BookingResponse getBookingById(int id){

    }
}
