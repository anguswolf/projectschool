package com.exercise.projectschool.service.impl;

import com.exercise.projectschool.entity.StudentEntity;
import com.exercise.projectschool.entity.TeacherEntity;
import com.exercise.projectschool.repository.TeacherRepository;
import com.exercise.projectschool.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {
    private final TeacherRepository teacherRepository;

    @Override
    public ResponseEntity<List<StudentEntity>> listStudentByTeacher(String serialNumber) {
        List<TeacherEntity> teachersSameSerialNumber = teacherRepository.findTeacherBySerialNumber(serialNumber);

        if (teachersSameSerialNumber.size() > 1) {
            log.info("Trovati piu insegnanti con il serialNumber: {}", serialNumber);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else if (!teachersSameSerialNumber.isEmpty()) {
            List<StudentEntity> studentFound = teachersSameSerialNumber.get(0).getStudententityList();

            log.info("Trovati gli studenti: {} del corso del professore: {}", studentFound, teachersSameSerialNumber);
            return new ResponseEntity<>(studentFound, HttpStatus.OK);
        } else {
            log.info("Nessun professore con il serialNumber: {}", serialNumber);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
