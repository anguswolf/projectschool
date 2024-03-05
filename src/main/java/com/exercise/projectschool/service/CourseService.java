package com.exercise.projectschool.service;

import com.exercise.projectschool.entity.StudentEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourseService {
    ResponseEntity<List<StudentEntity>> listStudentByTeacher(String serialNumber);
}
