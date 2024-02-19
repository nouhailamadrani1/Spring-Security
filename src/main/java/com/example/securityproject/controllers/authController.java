package com.example.securityproject.controllers;

import com.example.securityproject.dto.user.AuthenticateDto;
import com.example.securityproject.dto.user.RegisterDto;
import com.example.securityproject.dto.MessageDto;
import com.example.securityproject.services.interfaces.UserService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class authController {

    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<MessageDto> register(@Valid @RequestBody RegisterDto registerDto) throws ValidationException{
        return ResponseEntity.ok(userService.register(registerDto));
    }


    @PostMapping("authenticate")
    public ResponseEntity<MessageDto> authenticate(@Valid @RequestBody AuthenticateDto authenticateDto){
        return ResponseEntity.ok(userService.authenticate(authenticateDto));
    }
}
