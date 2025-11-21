package com.example.HotelBookingSystem.services;

import com.example.HotelBookingSystem.dto.LoginDTO;
import com.example.HotelBookingSystem.dto.RegistrationDTO;
import com.example.HotelBookingSystem.entity.User;
import com.example.HotelBookingSystem.repository.UserRepository;
import com.example.HotelBookingSystem.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;
import java.util.Optional;

//aaresidency01@gmail.com Aaresidency@123
@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final JavaMailSender mailSender;

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

    public void sentOtp(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("Email not found"));
        String otp = String.valueOf((int) Math.random() * 900000 + 100000);
        user.setOtp(otp);
        user.setOtpExpiry(LocalDateTime.now().plusMinutes(5));
        userRepository.save(user);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("OTP for reset password");
        message.setText("Your OTP for password reset is: "+ otp +"\nValid for 5 minutes.");
        mailSender.send(message);
    }

    public boolean verofyToken(String email, String otp){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("email not found"));
        if(!otp.equals(user.getOtp())){
            return false;
        }
        if(user.getOtpExpiry().isBefore(LocalDateTime.now())){
            return false;
        }
        user.setOtp(null);
        user.setOtpExpiry(null);
        userRepository.save(user);
        return true;
    }

    public void resetPassword(String email, String newPassword){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("Email not found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
