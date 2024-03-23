package com.exercise.projectschool.controller;

import com.exercise.projectschool.dto.TeacherStudentDTO;
import com.exercise.projectschool.entity.StudentEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CourseController {
    @GetMapping(path = "/studentsbyteacher/{serialNumber}")
    ResponseEntity<List<StudentEntity>> getStudentsByTeacher(@PathVariable String serialNumber);

    @GetMapping(path = "/studentsandteachersbyschool/{school}")
    ResponseEntity<TeacherStudentDTO> getTeachersAndStudentsSameSchool(@PathVariable String school);

    @PostMapping(path = "/addstudent/{teachersSerialNumber}")
    ResponseEntity<Void> addStudentToCourse(@RequestBody StudentEntity student, @PathVariable String teachersSerialNumber);

    @GetMapping(path = "/country/italy")
    String fetchDataItaly();
}
