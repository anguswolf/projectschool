package com.exercise.projectschool.service;

import com.exercise.projectschool.entity.StudentEntity;
import com.exercise.projectschool.entity.TeacherEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourseService {
    ResponseEntity<List<StudentEntity>> listStudentByTeacher(String serialNumber);

    ResponseEntity<Void> addStudentToCourse(StudentEntity student,String serialNumber);
}
