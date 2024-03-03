package com.exercise.projectschool.service;

import com.exercise.projectschool.entity.StudentEntity;
import com.exercise.projectschool.model.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {
    List<StudentEntity> getAllStudents();

    ResponseEntity<StudentEntity>  getStudentBySerialNumber(String serialNumber);

    ResponseEntity<Void> addStudents(List<Student> students);

    ResponseEntity<Void> updateStudent(Student student);

    ResponseEntity<Void> deleteStudentBySerialNumber(String serialNumber);

    ResponseEntity<Void> deleteAllStudents();
}
