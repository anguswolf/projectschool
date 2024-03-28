package com.exercise.projectschool.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

public interface UserController {
    @GetMapping("/anyone")
    ResponseEntity<?> getTestAPI1();
    @GetMapping("/manager")
    ResponseEntity<?> getTestAPI2(Principal principal);
    @GetMapping("/admin")
    ResponseEntity<?> getTestAPI3(Principal principal);
}
