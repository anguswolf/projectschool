package com.exercise.projectschool.controller;

import com.exercise.projectschool.entity.StudentEntity;
import com.exercise.projectschool.model.Student;
import com.exercise.projectschool.service.StudentService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path= "/student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping(path = "/{serialNumber}")
    public ResponseEntity<List<StudentEntity>> getStudent(@PathVariable String serialNumber) {
        return studentService.getStudentBySerialNumber(serialNumber);
    }
    @GetMapping(path= "/all")
    public ResponseEntity<List<StudentEntity>> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping(path= "/add")
    public ResponseEntity<Void> addStudents(@RequestBody List<Student> students) {
       return studentService.addStudents(students);
    }

    @PostMapping(path = "add/student/associated/teacher")
    public ResponseEntity<Void> addStudentAssociatedTeacher(@RequestBody StudentEntity student) {
        return studentService.addStudentAssociatedTeacher(student);
    }

    @DeleteMapping(path = "/delete/{serialNumber}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String serialNumber) {
        return studentService.deleteStudentBySerialNumber(serialNumber);
    }

    @DeleteMapping(path = "/delete/student/associated/teacher/{serialNumber}")
    public ResponseEntity<Void> deleteStudentAssociatedTeacher(@PathVariable String serialNumber){
        return studentService.deleteStudentassociatedeTeacher(serialNumber );
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Void> updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping(path = "/delete/all")
    public ResponseEntity<Void> deleteAllStudents() {
        return studentService.deleteAllStudents();
    }

}
