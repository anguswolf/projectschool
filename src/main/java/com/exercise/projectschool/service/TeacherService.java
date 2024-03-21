package com.exercise.projectschool.service;


import com.exercise.projectschool.dto.TeacherDTO;
import com.exercise.projectschool.entity.TeacherEntity;
import com.exercise.projectschool.model.Teacher;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeacherService {
    ResponseEntity<List<TeacherEntity>> getTeacherBySerialNumber(String serialNumber);

    ResponseEntity<TeacherDTO> getTeacherBySerialNumberWithJpa(String serialNumber);

    ResponseEntity<Void> addTeachers(List<Teacher> teachers);

    ResponseEntity<List<TeacherEntity>> getAllTeachers();

    ResponseEntity<List<TeacherDTO>> getAllTeachersWithJpa();

    ResponseEntity<Void> updateTeacher(Teacher teacher);

    ResponseEntity<Void> deleteTeacherBySerialNumber(String serialNumber);

    ResponseEntity<Void> deleteAllTeachers();
}
