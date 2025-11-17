package com.example.HotelBookingSystem.controller;

import com.example.HotelBookingSystem.dto.LoginDTO;
import com.example.HotelBookingSystem.dto.RegistrationDTO;
import com.example.HotelBookingSystem.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<Void> register(@RequestBody RegistrationDTO registrationDTO) {
        if (authService.register(registrationDTO))
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        String token = authService.login(loginDTO);
        if(token != null){
            return ResponseEntity.ok().body(Map.of(token,token));
        }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }

}

