package com.exercise.projectschool.controller;

import com.exercise.projectschool.dto.StudentDTO;
import com.exercise.projectschool.entity.StudentEntity;
import com.exercise.projectschool.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface StudentController {
    @GetMapping(path = "/{serialNumber}")
    ResponseEntity<List<StudentEntity>> getStudent(@PathVariable String serialNumber);

    @GetMapping(path = "/data/jpa/{serialNumber}")
    ResponseEntity<StudentDTO> getStudentWithJpa(@PathVariable String serialNumber);

    @GetMapping(path = "/all")
    ResponseEntity<List<StudentEntity>> getAllStudents();

    @GetMapping(path = "/all/data/jpa")
    ResponseEntity<List<StudentDTO>> getAllStudentsWithJpa();

    @PostMapping(path = "/add")
    ResponseEntity<Void> addStudents(@RequestBody List<Student> students);

    @PostMapping(path = "/add/kafka")
    ResponseEntity<Void> addStudentsToKafka(@RequestBody List<Student> students);

    @PostMapping(path = "/add/student/associated/teacher")
    ResponseEntity<Void> addStudentAssociatedTeacher(@RequestBody StudentEntity student);

    @DeleteMapping(path = "/delete/{serialNumber}")
    ResponseEntity<Void> deleteStudent(@PathVariable String serialNumber);

    @DeleteMapping(path = "/delete/student/associated/teacher/{serialNumber}")
    ResponseEntity<Void> deleteStudentAssociatedTeacher(@PathVariable String serialNumber);

    @PutMapping(path = "/update")
    ResponseEntity<Void> updateStudent(@RequestBody Student student);

    @DeleteMapping(path = "/delete/all")
    ResponseEntity<Void> deleteAllStudents();
}
