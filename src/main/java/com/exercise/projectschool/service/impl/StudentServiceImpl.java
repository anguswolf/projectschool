package com.exercise.projectschool.service.impl;

import com.exercise.projectschool.entity.StudentEntity;
import com.exercise.projectschool.model.Student;
import com.exercise.projectschool.repository.StudentRepository;
import com.exercise.projectschool.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public ResponseEntity<Void> saveStudent(List<Student> students) {
        {
            log.info("Received rolling stock data: {}", students);

            try {
                List<StudentEntity> studentEntities = new ArrayList<>();
                for (Student student : students) {
                    StudentEntity studentEntity = StudentEntity.builder()
                            .name(student.getName())
                            .city(student.getCity())
                            .age(student.getAge())
                            .school(student.getSchool()).build();
                    studentEntities.add(studentEntity);
                }

                studentRepository.saveAll(studentEntities);

                log.info("Scrittura sul DataBase avvenuta con successo: {}", studentEntities);
            } catch (Exception e) {
                log.error(e);
            }
            // Do any necessary processing with the received data
            return new ResponseEntity<>(HttpStatus.CREATED);
        }


    }
}
