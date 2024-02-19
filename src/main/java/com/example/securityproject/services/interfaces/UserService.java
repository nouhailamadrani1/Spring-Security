package com.example.securityproject.services.interfaces;

import com.example.securityproject.Entity.User;
import com.example.securityproject.dto.user.AuthenticateDto;
import com.example.securityproject.dto.MessageDto;
import com.example.securityproject.dto.user.RegisterDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;

public interface UserService {
    public User findUserByUsername(String username) throws EntityNotFoundException;
    public MessageDto register(RegisterDto registerDto) throws ValidationException;
    public MessageDto authenticate(AuthenticateDto authenticateDto);
    public MessageDto welcome();
}
