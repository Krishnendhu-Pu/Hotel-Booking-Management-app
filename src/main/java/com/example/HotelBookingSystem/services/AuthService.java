package com.example.HotelBookingSystem.services;

import com.example.HotelBookingSystem.dto.RegistrationDTO;
import com.example.HotelBookingSystem.entity.User;
import com.example.HotelBookingSystem.repository.UserRepository;
import com.example.HotelBookingSystem.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public boolean register(RegistrationDTO registrationDTO){
    User user = new User();
    user.setPassword(passwordEncoder.encode(registrationDTO.password()));
        User saved = userRepository.save(user);

        return saved.getId() != null;
    }
}
