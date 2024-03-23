package com.exercise.projectschool.controller;

import com.exercise.projectschool.dto.TeacherDTO;
import com.exercise.projectschool.entity.TeacherEntity;
import com.exercise.projectschool.model.Teacher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface TeacherController {
    @GetMapping(path = "/{serialNumber}")
    ResponseEntity<List<TeacherEntity>> getTeacherBySerialNumber(@PathVariable String serialNumber);

    @GetMapping(path = "/data/jpa/{serialNumber}")
    ResponseEntity<TeacherDTO> getTeacherWithJpa(@PathVariable String serialNumber);

    @GetMapping(path = "/all")
    ResponseEntity<List<TeacherEntity>> getAllTeachers();

    @GetMapping(path = "/all/data/jpa")
    ResponseEntity<List<TeacherDTO>> getAllTeachersWithJpa();

    @PostMapping(path = "/add")
    ResponseEntity<Void> addTeacher(@RequestBody List<Teacher> teachers);

    @PutMapping(path = "/update")
    ResponseEntity<Void> updateTeacher(@RequestBody Teacher teacher);

    @DeleteMapping(path = "/delete/{serialNumber}")
    ResponseEntity<Void> deleteTeacherBySerialNumber(@PathVariable String serialNumber);

    @DeleteMapping(path = "/delete/all")
    ResponseEntity<Void> deleteAllTeachers();
}
