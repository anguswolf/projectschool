package com.exercise.projectschool.controller.impl;

import com.exercise.projectschool.controller.CourseBookingController;
import com.exercise.projectschool.entity.CourseBookingEntity;
import com.exercise.projectschool.entity.TeacherEntity;
import com.exercise.projectschool.repository.TeacherRepository;
import com.exercise.projectschool.service.CourseBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/coursebooking")
public class CourseBookingControllerImpl implements CourseBookingController {
    private final CourseBookingService courseBookingService;
    private final TeacherRepository teacherRepository;

    @GetMapping(path = "/coursebyteacher/{id}")
    public ResponseEntity<List<CourseBookingEntity>> getBookingCourseByTeacher (@PathVariable Long id) {
        TeacherEntity teacher = teacherRepository.findTeacherEntityById(id);
        return courseBookingService.getBookingCourseByTeacher(teacher);
    }

}
