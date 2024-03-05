package com.exercise.projectschool.controller;

import com.exercise.projectschool.entity.StudentEntity;
import com.exercise.projectschool.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/course")
public class CourseController {
    private final CourseService courseService;

    @GetMapping(path = "/studentsbyteacher/{serialNumber}")
    public ResponseEntity<List<StudentEntity>> getStudentsByTeacher(@PathVariable String serialNumber) {
        return courseService.listStudentByTeacher(serialNumber);
    }
}
