package com.exercise.projectschool.repository;

import com.exercise.projectschool.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    List<StudentEntity> findBySerialNumberIgnoreCase(String serialNumber);

    List<StudentEntity> findStudentBySchool(String school);
}
