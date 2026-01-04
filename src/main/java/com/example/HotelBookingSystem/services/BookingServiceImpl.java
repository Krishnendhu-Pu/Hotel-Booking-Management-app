package com.example.HotelBookingSystem.services;

import com.example.HotelBookingSystem.dto.*;
import com.example.HotelBookingSystem.entity.Booking;
import com.example.HotelBookingSystem.repository.BookingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final WebClient webClient;
    private final BookingRepository bookingRepository;
    private final ObjectMapper objectMapper;

    @Override
    public BookingResponse createBooking(BookingRequest request){
        try{
        Booking booking = Booking.builder()
                .customerName(request.getCustomerName())
                .pax(request.getPax())
                .mobile(request.getMobile())
                .checkIn(request.getCheckIn())
                .checkOut(request.getCheckOut())
                .roomsJson(objectMapper.writeValueAsString(request.getRooms()))
                .advance(request.getAdvance())
                .advanceMode(request.getAdvanceMode())
                .kitchenRent(request.getKitchenRent())
                .discount(request.getDiscount())
                .gst(request.isGst())
                .gstAmount(request.getGstAmount())
                .totalAmount(request.getTotalAmount())
                .balanceAmount(request.getBalanceAmount())
                .remarks(request.getRemarks())
                .status("CONFIRMED")
                .createdAt(LocalDateTime.now())
                .build();

        Booking saved = bookingRepository.save(booking);

        return mapToResponse(saved);

    } catch (Exception e) {
        throw new RuntimeException("Failed to save booking", e);
    }

    }
    public BookingResponse updateBooking(int id, BookingRequest request){
        try {
            Booking booking = bookingRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Booking not found " + id));
            booking.setCustomerName(request.getCustomerName());
            booking.setPax(request.getPax());
            booking.setMobile(request.getMobile());
            booking.setCheckIn(request.getCheckIn());
            booking.setCheckOut(request.getCheckOut());
            booking.setRoomsJson(objectMapper.writeValueAsString(request.getRooms()));
            booking.setAdvance(request.getAdvance());
            booking.setAdvanceMode(request.getAdvanceMode());
            booking.setKitchenRent(request.getKitchenRent());
            booking.setDiscount(request.getDiscount());
            booking.setGst(request.isGst());
            booking.setGstAmount(request.getGstAmount());
            booking.setTotalAmount(request.getTotalAmount());
            booking.setBalanceAmount(request.getBalanceAmount());
            booking.setRemarks(request.getRemarks());
            booking.setStatus(request.getStatus());
            booking.setCreatedAt(LocalDateTime.now());
            Booking updated = bookingRepository.save(booking);
            return mapToResponse(updated);
        }catch (Exception e){
            throw new RuntimeException("Failed to update booking", e);
        }
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

    public Mono<String> addRoomViaRoomService(RoomsRequestDTO roomsRequestDTO){

        return webClient.post()
                .uri("http://localhost:8081/api/rooms")
                .bodyValue(roomsRequestDTO)
                .retrieve()
                .bodyToMono(String.class);
    }

    public List<RoomsResponseDTO> getRooms(){

        return webClient.get()
                .uri("http://localhost:8081/api/rooms/roomslist")
                .retrieve()
                .bodyToFlux(RoomsResponseDTO.class)
                .collectList()
                .block();
    }

    public BookingResponse mapToResponse(Booking booking){


        try {
            List<RoomDTO> rooms =
                    objectMapper.readValue(
                            booking.getRoomsJson(),
                            objectMapper.getTypeFactory()
                                    .constructCollectionType(List.class, RoomDTO.class)
                    );
            return BookingResponse.builder()
                    .id(booking.getId())
                    .customerName(booking.getCustomerName())
                    .pax(booking.getPax())
                    .mobile(booking.getMobile())
                    .checkIn(booking.getCheckIn())
                    .checkOut(booking.getCheckOut())
                    .rooms(rooms)
                    .advance(booking.getAdvance())
                    .advanceMode(booking.getAdvanceMode())
                    .kitchenRent(booking.getKitchenRent())
                    .discount(booking.getDiscount())
                    .gst(booking.isGst())
                    .gstAmount(booking.getGstAmount())
                    .totalAmount(booking.getTotalAmount())
                    .balanceAmount(booking.getBalanceAmount())
                    .remarks(booking.getRemarks())
                    .status(booking.getStatus())
                    .createdAt(booking.getCreatedAt())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse room data", e);
        }
    }


}
