package com.exercise.projectschool.repository;

import com.exercise.projectschool.entity.CourseBookingEntity;
import com.exercise.projectschool.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseBookingRepository extends JpaRepository<CourseBookingEntity, Long> {
    List<CourseBookingEntity> findAllByTeacher(TeacherEntity teacher);
}
