package com.exercise.projectschool.controller;

import com.exercise.projectschool.entity.StudentEntity;
import com.exercise.projectschool.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path = "/addstudent/{teachersSerialNumber}")
    public ResponseEntity<Void> addStudentToCourse (@RequestBody StudentEntity student, @PathVariable String teachersSerialNumber) {
        return courseService.addStudentToCourse(student, teachersSerialNumber);
    }

}
