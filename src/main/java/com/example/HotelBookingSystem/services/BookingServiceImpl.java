package com.example.HotelBookingSystem.services;

import com.example.HotelBookingSystem.dto.BookingRequest;
import com.example.HotelBookingSystem.dto.BookingResponse;
import com.example.HotelBookingSystem.entity.Booking;
import com.example.HotelBookingSystem.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    @Override
    public List<BookingResponse> getAllBookings(){
        return bookingRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    @Override
    public BookingResponse getBookingById(int id){
        Booking booking= bookingRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Booking not found " +id));
        return mapToResponse(booking);

    }

    public BookingResponse updateBooking(int id, BookingRequest request){
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Booking not found "+id));
        booking.setCustomerName(request.getCustomerName());
        booking.setRoomType(request.getRoomType());
        booking.setBookingDate(request.getBookingDate());
        booking.setStatus(request.getStatus());
        Booking updated = bookingRepository.save(booking);
        return mapToResponse(updated);
    }

    @Override
    public void deleteBooking( int id){
        bookingRepository.deleteById(id);
    }

    public List<BookingResponse> getBookingByDateRange(LocalDate startDate, LocalDate endDate){
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);

        return bookingRepository.findBookingByDateRange(startDateTime, endDateTime)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

    }
    public BookingResponse mapToResponse(Booking booking){
        return BookingResponse.builder()
                .id(booking.getId())
                .customerName(booking.getCustomerName())
                .roomType(booking.getRoomType())
                .bookingDate(booking.getBookingDate())
                .status(booking.getStatus())
                .build();
    }


}
