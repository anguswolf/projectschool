package com.exercise.projectschool.controller;

import com.exercise.projectschool.entity.StudentEntity;
import com.exercise.projectschool.model.Student;
import com.exercise.projectschool.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path= "/school")
public class StudentController {
    private final StudentService studentService;

    @GetMapping(path= "/student")
    public List<StudentEntity> getStudent() {
        return studentService.getAllStudents();
    }

    @PostMapping(path= "/add/student")
    public ResponseEntity<Void> postStudent(@RequestBody List<Student> students) {
       return studentService.saveStudent(students);
    }
}
