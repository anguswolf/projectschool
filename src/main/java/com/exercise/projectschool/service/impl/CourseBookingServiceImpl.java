package com.exercise.projectschool.service.impl;

import com.exercise.projectschool.entity.CourseBookingEntity;
import com.exercise.projectschool.entity.TeacherEntity;
import com.exercise.projectschool.repository.CourseBookingRepository;
import com.exercise.projectschool.service.CourseBookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class CourseBookingServiceImpl implements CourseBookingService {
    private final CourseBookingRepository courseBookingRepository;


    @Override
    public ResponseEntity<List<CourseBookingEntity>> getBookingCourseByTeacher(TeacherEntity teacher) {
        List<CourseBookingEntity> getCourseByTeacher = courseBookingRepository.findAllByTeacher(teacher);
        try {
            if(!getCourseByTeacher.isEmpty()) {
                log.info("Prenotazione dei corsi dell insegnante");
                return new ResponseEntity<>(getCourseByTeacher, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error(e);
        }
        log.info("Nessuna prenotazione per l'insegnante");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
