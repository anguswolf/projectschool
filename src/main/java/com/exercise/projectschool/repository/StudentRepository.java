package com.exercise.projectschool.repository;

import com.exercise.projectschool.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    List<StudentEntity> findBySerialNumberIgnoreCase(String serialNumber);
}
