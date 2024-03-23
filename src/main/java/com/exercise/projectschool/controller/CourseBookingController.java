package com.exercise.projectschool.controller;

import com.exercise.projectschool.entity.CourseBookingEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CourseBookingController {
    @GetMapping(path = "/coursebyteacher/{id}")
    ResponseEntity<List<CourseBookingEntity>> getBookingCourseByTeacher(@PathVariable Long id);
}
