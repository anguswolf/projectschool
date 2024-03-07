package com.exercise.projectschool.service.impl;


import com.exercise.projectschool.client.CountryClient;
import com.exercise.projectschool.commonUtils.CommonUtils;
import com.exercise.projectschool.dto.TeacherStudentDTO;
import com.exercise.projectschool.entity.StudentEntity;
import com.exercise.projectschool.entity.TeacherEntity;
import com.exercise.projectschool.repository.StudentRepository;
import com.exercise.projectschool.repository.TeacherRepository;
import com.exercise.projectschool.service.CourseService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final CountryClient countryClient;

    @Override
    public ResponseEntity<List<StudentEntity>> listStudentByTeacher(String serialNumber) {
        List<TeacherEntity> teachersSameSerialNumber = teacherRepository.findTeacherBySerialNumber(serialNumber);

        if (teachersSameSerialNumber.size() > 1) {
            log.info("Trovati piu insegnanti con il serialNumber: {}", serialNumber);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else if (!teachersSameSerialNumber.isEmpty()) {
            List<StudentEntity> studentFound = teachersSameSerialNumber.get(0).getStudententityList();
            return new ResponseEntity<>(studentFound, HttpStatus.OK);
        } else {
            log.info("Nessun professore con il serialNumber: {}", serialNumber);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<TeacherStudentDTO> getTeachersAndStudentsSameSchool(String school) {
        List<TeacherEntity> teachersBySchool;
        try {
            teachersBySchool = teacherRepository.findTeacherBySchool(school);
            List<StudentEntity> studentsBySchool = studentRepository.findStudentBySchool(school);
            TeacherStudentDTO teacherStudentDTO;

            if (teachersBySchool.isEmpty()) {
                log.info("Insegnati non presenti nella scuola");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else if (studentsBySchool.isEmpty()) {
                log.info("Insegnanti senza studenti nella scuola {}", school);
            } else {
                log.info("Insegnati e studenti nella stessa scuola: {}", school);
            }
            teacherStudentDTO = CommonUtils.buildTeacherStudentDTO(studentsBySchool, teachersBySchool, school);
            return new ResponseEntity<>(teacherStudentDTO, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<Void> addStudentToCourse(StudentEntity student, String serialNumber) {
        try {
            List<TeacherEntity> teachers = teacherRepository.findTeacherBySerialNumber(serialNumber);
            if (teachers.size() > 1) {
                log.info("Piu Insegnanti presenti con il serialNumber: {}", serialNumber);
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else if (!teachers.isEmpty()) {
                TeacherEntity teacherToCourse = teacherRepository.findTeacherBySerialNumber(serialNumber).get(0);

                Optional<StudentEntity> optionalStudent = studentRepository.findBySerialNumberIgnoreCase(student.getSerialNumber()).stream().findFirst();
                if (optionalStudent.isPresent()) {
                    StudentEntity studentToCourse = studentRepository.findBySerialNumberIgnoreCase(student.getSerialNumber()).get(0);
                    StudentEntity studentUpdated = StudentEntity.builder()
                            .id(studentToCourse.getId())
                            .name(student.getName())
                            .city(student.getCity())
                            .age(student.getAge())
                            .school(student.getSchool())
                            .serialNumber(student.getSerialNumber()).build();

                    studentUpdated.setTeacherEntity(teacherToCourse);
                    studentRepository.save(studentUpdated);
                } else {
                    student.setTeacherEntity(teacherToCourse);
                    studentRepository.save(student);
                }


                log.info("Salvato il nuovo studente associato all' insegnante con il serialNumber: {}", serialNumber);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                log.info("Insegnante non presente nel DB con il serialNumber: {}", serialNumber);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @Override
    public String fetchDataItaly() {
        String response = countryClient.callExternalService();

        // Estrai la capitale dall'oggetto JSON restituito
        try {
            // Considerando che la risposta sia un array JSON contenente un singolo elemento
            JsonNode countryJson = new ObjectMapper().readTree(response).get(0);
            JsonNode capitalNode = countryJson.get("capital");
            if (capitalNode != null && capitalNode.isArray() && !capitalNode.isEmpty()) {
                String capital = capitalNode.get(0).asText();
                return capital;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
