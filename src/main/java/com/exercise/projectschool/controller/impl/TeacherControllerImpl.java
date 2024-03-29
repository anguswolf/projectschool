package com.exercise.projectschool.controller.impl;

import com.exercise.projectschool.controller.TeacherController;
import com.exercise.projectschool.dto.TeacherDTO;
import com.exercise.projectschool.entity.TeacherEntity;
import com.exercise.projectschool.model.Teacher;
import com.exercise.projectschool.service.TeacherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path= "/teacher")
@Tag(name = "Teacher Management")
public class TeacherControllerImpl implements TeacherController {

    private final TeacherService teacherService;

    @GetMapping(path = "/{serialNumber}")
    public ResponseEntity<List<TeacherEntity>> getTeacherBySerialNumber(@PathVariable String serialNumber) {
        return teacherService.getTeacherBySerialNumber(serialNumber);
    }

    @GetMapping(path = "/data/jpa/{serialNumber}")
    public ResponseEntity<TeacherDTO> getTeacherWithJpa(@PathVariable String serialNumber) {
        return teacherService.getTeacherBySerialNumberWithJpa(serialNumber);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<TeacherEntity>> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping(path = "/all/data/jpa")
    public ResponseEntity<List<TeacherDTO>> getAllTeachersWithJpa() {
        return teacherService.getAllTeachersWithJpa();
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Void> addTeacher(@RequestBody List<Teacher> teachers) {
        return teacherService.addTeachers(teachers);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Void> updateTeacher(@RequestBody Teacher teacher) {
        return teacherService.updateTeacher(teacher);
    }

    @DeleteMapping(path = "/delete/{serialNumber}")
    public ResponseEntity<Void> deleteTeacherBySerialNumber(@PathVariable String serialNumber) {
        return teacherService.deleteTeacherBySerialNumber(serialNumber);
    }

    @DeleteMapping(path = "/delete/all")
    public ResponseEntity<Void> deleteAllTeachers() {
        return teacherService.deleteAllTeachers();
    }
}