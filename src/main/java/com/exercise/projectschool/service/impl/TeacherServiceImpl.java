package com.exercise.projectschool.service.impl;

import com.exercise.projectschool.commonUtils.CommonUtils;
import com.exercise.projectschool.entity.TeacherEntity;
import com.exercise.projectschool.model.Teacher;
import com.exercise.projectschool.repository.TeacherRepository;
import com.exercise.projectschool.service.TeacherService;
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
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public ResponseEntity<List<TeacherEntity>> getTeacherBySerialNumber(String serialNumber) {
        List<TeacherEntity> listAllTeachers = teacherRepository.findTeacherBySerialNumber(serialNumber);
        if (!listAllTeachers.isEmpty()) {
            log.info("Trovati insegnanti con il serialNumber: {}", serialNumber);
            return new ResponseEntity<>(listAllTeachers, HttpStatus.OK);
        } else {
            log.info("Nessun insegnante con il serialNumber: {}", serialNumber);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<TeacherEntity>> getAllTeachers() {
        List<TeacherEntity> listAllTeachers = teacherRepository.findAll();
        log.info("Insegnanti presenti: {}", listAllTeachers);

        return new ResponseEntity<>(listAllTeachers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addTeachers(List<Teacher> teachers) {
        try {
            List<TeacherEntity> listTeachersEntity = new ArrayList<>();
            for (Teacher teacher : teachers) {
                if (teacherRepository.findTeacherBySerialNumber(teacher.getSerialNumber()).isEmpty()) {
                    listTeachersEntity.add(CommonUtils.buildTeacherEntity(teacher));
                } else {
                    log.info("Insegnante gia presente con il serialNumber: {}", teacher.getSerialNumber());
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }
            }
            teacherRepository.saveAll(listTeachersEntity);

            log.info("Insegnanti aggiunti: {}", listTeachersEntity);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Void> updateTeacher(Teacher teacher) {
        String serialNumber = teacher.getSerialNumber();
        List<TeacherEntity> teachersWithSameSerial = teacherRepository.findTeacherBySerialNumber(serialNumber);

        // Controlla se ci sono più di un record con lo stesso serialNumber
        if (teachersWithSameSerial.size() > 1) {
            log.info("Trovati più insegnanti con lo stesso serialNumber: {}", serialNumber);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else if (!teachersWithSameSerial.isEmpty()) {
            TeacherEntity teacherToUpdate = teachersWithSameSerial.get(0);
            TeacherEntity teacherUpdated = TeacherEntity.builder()
                    .id(teacherToUpdate.getId()) // Assicurati di impostare anche l'ID
                    .name(teacher.getName())
                    .email(teacher.getEmail())
                    .matter(teacher.getMatter())
                    .school(teacher.getSchool())
                    .serialNumber(teacher.getSerialNumber())
                    .build();
            teacherRepository.save(teacherUpdated);
            log.info("Insegnante aggiornato nel database: {}", teacherUpdated);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            log.info("Nessun insegnante trovato con serialNumber: {}", serialNumber);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Void> deleteTeacherBySerialNumber(String serialNumber) {
        List<TeacherEntity> teachersWithSameSerial = teacherRepository.findTeacherBySerialNumber(serialNumber);

        if(!teachersWithSameSerial.isEmpty()) {
            teacherRepository.deleteAll(teachersWithSameSerial);
            log.info("Tutti gli insegnanti eliminati con serialNumber: {}", serialNumber);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            log.info("Nessun Insegnante trovato con serialNumber {}", serialNumber);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Void> deleteAllTeachers() {
        List<TeacherEntity> allTeachersFromDb = teacherRepository.findAll();

        if(!allTeachersFromDb.isEmpty()) {
            teacherRepository.deleteAll();
            log.info("Tutti gli insegnanti {} eliminati dal database", allTeachersFromDb);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            log.info("Nessun Insegnante trovato nel Database");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
