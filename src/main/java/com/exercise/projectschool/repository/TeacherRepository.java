package com.exercise.projectschool.repository;


import com.exercise.projectschool.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
    List<TeacherEntity> findTeacherBySerialNumber(String serialNumber);
}
