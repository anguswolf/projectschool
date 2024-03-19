package com.exercise.projectschool.service;

import com.exercise.projectschool.entity.StudentEntity;
import com.exercise.projectschool.model.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {
    ResponseEntity<List<StudentEntity>> getAllStudents();

    ResponseEntity<List<StudentEntity>>  getStudentBySerialNumber(String serialNumber);

    ResponseEntity<Void> addStudents(List<Student> students);

    ResponseEntity<Void> addStudentAssociatedTeacher(StudentEntity student);

    ResponseEntity<Void> updateStudent(Student student);

    ResponseEntity<Void> deleteStudentBySerialNumber(String serialNumber);

    ResponseEntity<Void> deleteAllStudents();

    ResponseEntity<Void> deleteStudentAssociatedTeacher(String serialNumber);
}
