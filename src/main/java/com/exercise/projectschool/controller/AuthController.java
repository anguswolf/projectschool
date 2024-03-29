package com.exercise.projectschool.controller;

import com.exercise.projectschool.dto.UserRegistrationDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

public interface AuthController {
    @PostMapping("/sign-in")
    ResponseEntity<?> authenticateUser(Authentication authentication, HttpServletResponse response);

    @PostMapping ("/refresh-token")
    ResponseEntity<?> getAccessToken(String authorizationHeader);

    @PostMapping("/sign-up")
    ResponseEntity<?> registerUser(UserRegistrationDto userRegistrationDto, BindingResult bindingResult,
                                   HttpServletResponse httpServletResponse);
}
