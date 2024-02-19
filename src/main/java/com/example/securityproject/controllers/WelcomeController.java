package com.example.securityproject.controllers;


import com.example.securityproject.dto.MessageDto;
import com.example.securityproject.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/welcome")
@RequiredArgsConstructor
public class WelcomeController {
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<MessageDto> welcome(){
        return ResponseEntity.ok(this.userService.welcome());
    }

}
