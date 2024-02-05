package com.spring.security6.service;

import com.spring.security6.dto.ChangePasswordRequestDto;
import com.spring.security6.entity.AppUser;

import java.security.Principal;

public interface UserService {
   void changePassword(ChangePasswordRequestDto request, Principal connectedUser);

}