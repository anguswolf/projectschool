package com.exercise.projectschool.repository;

import com.exercise.projectschool.entity.StudentEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    List<StudentEntity> findBySerialNumberIgnoreCase(String serialNumber);

    StudentEntity findSingleBySerialNumberIgnoreCase(String serialNumber);

    List<StudentEntity> findStudentBySchool(String school);
}
