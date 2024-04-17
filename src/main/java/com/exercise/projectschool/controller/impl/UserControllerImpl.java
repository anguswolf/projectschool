package com.exercise.projectschool.controller.impl;

import com.exercise.projectschool.controller.UserController;
import com.exercise.projectschool.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserRepository userRepo;


    @GetMapping("/anyone")
    public ResponseEntity<?> getTestAPI1() {
        return ResponseEntity.ok("Response");
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<?> getTestAPI2(Principal principal) {
        return ResponseEntity.ok(principal.getName() + " : is accessing admin api. All data from backend" + userRepo.findAll());
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    @GetMapping("/manager")
    public ResponseEntity<?> getTestAPI3(Principal principal) {
        return ResponseEntity.ok(principal.getName() + " : is accessing manager api. All data from backend" + userRepo.findAll());
    }
}
