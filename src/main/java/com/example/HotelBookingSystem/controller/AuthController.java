package com.example.HotelBookingSystem.controller;

import com.example.HotelBookingSystem.dto.RegistrationDTO;
import com.example.HotelBookingSystem.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Auth")
public class AuthController {

        private final AuthService authService;

        @PostMapping("/registration")
        public ResponseEntity<Void> register(@RequestBody RegistrationDTO registrationDTO){
            if(authService.register(registrationDTO))
            return ResponseEntity.ok().build();
            return ResponseEntity.badRequest().build();
        }
}
