package com.exercise.projectschool.service;

import com.exercise.projectschool.dto.AuthResponseDTO;
import com.exercise.projectschool.dto.UserRegistrationDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

public interface AuthService {
    AuthResponseDTO getJwtTokensAfterAuthentication(Authentication authentication, HttpServletResponse response);
    Object getAccessTokenUsingRefreshToken(String authorizationHeader);
    AuthResponseDTO registerUser(UserRegistrationDto userRegistrationDto, HttpServletResponse httpServletResponse);
}
