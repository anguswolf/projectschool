package com.exercise.projectschool.service.impl;

import com.exercise.projectschool.entity.ClassRoomEntity;
import com.exercise.projectschool.entity.StudentEntity;
import com.exercise.projectschool.repository.ClassRoomRepository;
import com.exercise.projectschool.service.ClassRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClassRoomServiceImpl implements ClassRoomService {
    private final ClassRoomRepository classRoomRepository;
    @Override
    public ResponseEntity<ClassRoomEntity> addClassRoom(String classRoom) {
        List<ClassRoomEntity> existingClassRoom = classRoomRepository.findByClassRoom(classRoom);

        if(!existingClassRoom.isEmpty()) {
            log.info("Classe {} già esistente", classRoom);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }else {

            ClassRoomEntity classRoomToSave = ClassRoomEntity.builder()
                    .classRoom(classRoom).build();

            classRoomRepository.save(classRoomToSave);

            log.info("Classe Aggiunta {} ", classRoomToSave);
            return new ResponseEntity<>(classRoomToSave, HttpStatus.OK);
        }
    }
    @Cacheable(cacheNames = "findAllClassRoom", cacheManager = "cacheManager")
    @Override
    public ResponseEntity<List<ClassRoomEntity>> getAllClassRoom() {
        List<ClassRoomEntity> listAllClassRoom = classRoomRepository.findAll();

        return new ResponseEntity<>(listAllClassRoom, HttpStatus.OK);
    }
}
