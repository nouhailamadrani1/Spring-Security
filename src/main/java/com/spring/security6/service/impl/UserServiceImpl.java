package com.spring.security6.service.impl;

import com.spring.security6.dto.ChangePasswordRequestDto;
import com.spring.security6.entity.AppUser;
import com.spring.security6.repository.UserRepository;
import com.spring.security6.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {



        private final PasswordEncoder passwordEncoder;
        private final UserRepository repository;
        @Override
        public void changePassword(ChangePasswordRequestDto request, Principal connectedUser) {

            var user = (AppUser) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

            // check if the current password is correct
            if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
                throw new IllegalStateException("Wrong password");
            }
            // check if the two new passwords are the same
            if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
                throw new IllegalStateException("Password are not the same");
            }

            // update the password
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));

            // save the new password
            repository.save(user);
        }
}