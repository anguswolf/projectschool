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
@RequestMapping(path= "/student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping(path = "/{serialNumber}")
    public ResponseEntity<StudentEntity> getStudent(@PathVariable String serialNumber) {
        return studentService.getStudentBySerialNumber(serialNumber);
    }

    @GetMapping(path= "/all")
    public List<StudentEntity> getStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping(path= "/add")
    public ResponseEntity<Void> addStudents(@RequestBody List<Student> students) {
       return studentService.addStudents(students);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Void> updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }
}
