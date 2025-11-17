package com.example.HotelBookingSystem.services;

import com.example.HotelBookingSystem.dto.LoginDTO;
import com.example.HotelBookingSystem.dto.RegistrationDTO;
import com.example.HotelBookingSystem.entity.User;
import com.example.HotelBookingSystem.repository.UserRepository;
import com.example.HotelBookingSystem.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public boolean register(RegistrationDTO registrationDTO){
        System.out.println("Register API called" +registrationDTO);
        User user = new User();
        user.setPassword(passwordEncoder.encode(registrationDTO.password()));
        user.setUsername(registrationDTO.username());
        user.setEmail(registrationDTO.email());
        User saved = userRepository.save(user);
        return saved.getId() != null;
    }

    public String login(LoginDTO loginDTO){
        System.out.println(loginDTO.userName()+" "+loginDTO.password());
        Optional<User> userOpt = userRepository.findByUsername(loginDTO.userName());
        if(userOpt.isPresent()){
        User user= userOpt.get();
        boolean matches = passwordEncoder.matches(loginDTO.password(),user.getPassword());
        System.out.println(loginDTO.password()+" "+user.getPassword());
        if(matches){
            return jwtUtil.generateToken(user.getEmail());
        }
        }
        return null;
    }
}
