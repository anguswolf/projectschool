package com.exercise.projectschool.service;

import com.exercise.projectschool.entity.CourseBookingEntity;
import com.exercise.projectschool.entity.TeacherEntity;
import com.exercise.projectschool.model.Teacher;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourseBookingService {

    ResponseEntity<List<CourseBookingEntity>> getBookingCourseByTeacher(TeacherEntity teacher);
}
