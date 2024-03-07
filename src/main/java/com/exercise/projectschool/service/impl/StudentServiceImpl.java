package com.exercise.projectschool.service.impl;

import com.exercise.projectschool.commonUtils.CommonUtils;
import com.exercise.projectschool.entity.StudentEntity;
import com.exercise.projectschool.model.Student;
import com.exercise.projectschool.repository.StudentRepository;
import com.exercise.projectschool.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
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
    private final CourseServiceImpl courseServiceImpl;

    @Cacheable(cacheNames = "findAllStudent", cacheManager = "cacheManager")
    @Override
    public ResponseEntity<List<StudentEntity>> getAllStudents() {
        List<StudentEntity> listAllStudents = studentRepository.findAll();

        return new ResponseEntity<>(listAllStudents, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<StudentEntity>> getStudentBySerialNumber(String serialNumber) {
        List<StudentEntity> listAllStudents = studentRepository.findBySerialNumberIgnoreCase(serialNumber);
        if (!listAllStudents.isEmpty()) { // Se c'è solo uno o piu record con lo stesso serialNumber
            log.info("Trovati studenti {} con il serialNumber: {}", listAllStudents, serialNumber);
            return new ResponseEntity<>(listAllStudents, HttpStatus.OK); // Restituisci lo studente trovato con codice di stato 200 OK
        } else {
            log.info("Non esiste uno studente con questo serialNumber: {}", serialNumber);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Restituisci un codice di stato 404 Not Found
        }
    }


    @Override
    public ResponseEntity<Void> addStudents(List<Student> students) {
        {
            try {
                List<StudentEntity> studentEntities = new ArrayList<>();
                for (Student student : students) {
                    if (studentRepository.findBySerialNumberIgnoreCase(student.getSerialNumber()).isEmpty()) {
                        String capitalCity = courseServiceImpl.fetchDataItaly();
                        studentEntities.add(CommonUtils.buildStudentEntity(student, capitalCity));
                    } else {
                        log.info("Studente gia presente con il serialNumber: {}", student.getSerialNumber());
                        return new ResponseEntity<>(HttpStatus.CONFLICT);
                    }
                }
                studentRepository.saveAll(studentEntities);

                log.info("Studenti aggiunti: {}", studentEntities);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (Exception e) {
                log.error(e);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> updateStudent(Student student) {
        String serialNumber = student.getSerialNumber();

        //Trova tutti gli studenti con lo stesso serialNumber
        List<StudentEntity> studentsWithSameSerial = studentRepository.findBySerialNumberIgnoreCase(serialNumber);

        // Controlla se ci sono più di un record con lo stesso serialNumber
        if (studentsWithSameSerial.size() > 1) {
            log.error("Trovati più studenti con lo stesso serialNumber: {}", serialNumber);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else if (!studentsWithSameSerial.isEmpty()) { // Se c'è solo un record con lo stesso serialNumber
            StudentEntity studentToUpdate = studentsWithSameSerial.get(0);
            StudentEntity updatedStudent = StudentEntity.builder()
                    .id(studentToUpdate.getId()) // Assicurati di impostare anche l'ID
                    .name(student.getName())
                    .city(student.getCity())
                    .age(student.getAge())
                    .school(student.getSchool())
                    .serialNumber(student.getSerialNumber())
                    .build();

            // Salva il nuovo oggetto aggiornato nel repository
            studentRepository.save(updatedStudent);

            log.info("Studente aggiornato nel database: {}", updatedStudent);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            log.info("Nessun Studente trovato con serialNumber: {}", serialNumber);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public ResponseEntity<Void> deleteStudentBySerialNumber(String serialNumber) {
        List<StudentEntity> studentSameSerialNumber = studentRepository.findBySerialNumberIgnoreCase(serialNumber);

        // Controlla se ci sono uno o più di un record con lo stesso serialNumber
        if (!studentSameSerialNumber.isEmpty()) {
            studentRepository.deleteAll(studentSameSerialNumber);
            log.info("Tutti gli studenti {} eliminati con serialNumber: {}", studentSameSerialNumber, serialNumber);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            log.info("Nessun Studente trovato con serialNumber {}", serialNumber);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Void> deleteAllStudents() {
        List<StudentEntity> allStudentsFromDb = studentRepository.findAll();

        if(!allStudentsFromDb.isEmpty()) {
            studentRepository.deleteAll();
            log.info("Tutti gli studenti: {} eliminati dal database", allStudentsFromDb);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            log.info("Nessun Studente trovato nel Database");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
