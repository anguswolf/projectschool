package com.exercise.projectschool.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

public interface DashboardController {
    @GetMapping("/welcome-message")
    ResponseEntity<String> getFirstWelcomeMessage(Authentication authentication);

    @GetMapping("/manager-message")
    ResponseEntity<String> getManagerData(Principal principal);

    @PreAuthorize("hasAuthority('SCOPE_WRITE')")
    @PostMapping("/admin-message")
    ResponseEntity<String> getAdminData(String message, Principal principal);
}
