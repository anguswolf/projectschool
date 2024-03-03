package com.exercise.projectschool.service.impl;

import com.exercise.projectschool.commonUtils.CommonUtils;
import com.exercise.projectschool.entity.StudentEntity;
import com.exercise.projectschool.model.Student;
import com.exercise.projectschool.repository.StudentRepository;
import com.exercise.projectschool.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
    public ResponseEntity<StudentEntity> getStudentBySerialNumber(String serialNumber) {
        List<StudentEntity> listAllStudents = studentRepository.findBySerialNumberIgnoreCase(serialNumber);
        // Controlla se ci sono più di un record con lo stesso serialNumber
        if (listAllStudents.size() > 1) {
            log.error("Trovati più studenti con lo stesso serialNumber: {}", serialNumber);
            return new ResponseEntity<>(HttpStatus.CONFLICT); // Restituisci un codice di stato 409 Conflict
        } else if (!listAllStudents.isEmpty()) { // Se c'è solo un record con lo stesso serialNumber
            StudentEntity student = listAllStudents.get(0);
            log.info("Trovato lo studente con questo serialNumber: {}", serialNumber);
            return new ResponseEntity<>(student, HttpStatus.OK); // Restituisci lo studente trovato con codice di stato 200 OK
        } else {
            log.info("Non esiste uno studente con questo serialNumber: {}", serialNumber);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Restituisci un codice di stato 404 Not Found
        }
    }


    @Override
    public ResponseEntity<Void> addStudents(List<Student> students) {
        {
            log.info("Received for student: {}", students);

            try {
                List<StudentEntity> studentEntities = new ArrayList<>();
                for (Student student : students) {
                    studentEntities.add(CommonUtils.buildStudent(student));
                }

                studentRepository.saveAll(studentEntities);

                log.info("Scrittura sul DataBase avvenuta con successo: {}", studentEntities);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (Exception e) {
                log.error(e);
            }
            // Do any necessary processing with the received data
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> updateStudent(Student student) {
        log.info("Received for student: {}", student);
        String serialNumber = student.getSerialNumber();


        // Trova tutti gli studenti con lo stesso serialNumber
        List<StudentEntity> studentsWithSameSerial = studentRepository.findBySerialNumberIgnoreCase(serialNumber);

        // Controlla se ci sono più di un record con lo stesso serialNumber
        if (studentsWithSameSerial.size() > 1) {
            log.error("Trovati più studenti con lo stesso serialNumber: {}", serialNumber);
            return new ResponseEntity<>(HttpStatus.CONFLICT); // Restituisci un codice di stato 409 Conflict
        } else if (!studentsWithSameSerial.isEmpty()) { // Se c'è solo un record con lo stesso serialNumber
            StudentEntity studentToUpdate = studentsWithSameSerial.get(0);
            studentToUpdate.setName(student.getName());
            studentToUpdate.setCity(student.getCity());
            studentToUpdate.setAge(student.getAge());
            studentToUpdate.setSchool(student.getSchool());
            studentRepository.save(studentToUpdate);
            log.info("Studente aggiornato nel database: {}", studentToUpdate);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            log.info("Nessun Studente trovato con serialNumber: {}", serialNumber);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Void> deleteStudentBySerialNumber(String serialNumber) {
        log.info("Received for student: {}", serialNumber);        // Trova tutti gli studenti con lo stesso serialNumber
        List<StudentEntity> studentSameSerialNumber = studentRepository.findBySerialNumberIgnoreCase(serialNumber);

        int countStudent = 1;

        // Controlla se ci sono più di un record con lo stesso serialNumber
        if(studentSameSerialNumber.size() > 1) {
            /*log.error("Trovati piu studenti con lo stesso serialNumber: {}", serialNumber);
            return new ResponseEntity<>(HttpStatus.CONFLICT); // Restituisci un codice di stato 409 Conflict*/
            for (StudentEntity student: studentSameSerialNumber) {
                studentRepository.delete(student);
                log.info("Studente {} eliminato dal database: {}", countStudent, student);
                countStudent++;
            }
            log.info("Tutti gli studenti eliminati con serialNumber {}", serialNumber);
            return new ResponseEntity<>(HttpStatus.OK);
        }else if (!studentSameSerialNumber.isEmpty()) {   //Solo uno studente con lo stesso serialNumber
            StudentEntity studentToDelete = studentSameSerialNumber.get(0);
            studentRepository.delete(studentToDelete);
            log.info("Studente eliminato dal database: {}", studentToDelete);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            log.info("Nessun Studente trovato con serialNumber {}", serialNumber);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Void> deleteAllStudents() {
        studentRepository.deleteAll();
        log.info("Tutti gli studenti eliminati dal database");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
