package com.exercise.projectschool.service.impl;

import com.exercise.projectschool.commonUtils.CommonUtils;
import com.exercise.projectschool.dto.TeacherStudentClassRoomDTO;
import com.exercise.projectschool.entity.ClassRoomEntity;
import com.exercise.projectschool.entity.StudentEntity;
import com.exercise.projectschool.entity.TeacherEntity;
import com.exercise.projectschool.model.Student;
import com.exercise.projectschool.model.Teacher;
import com.exercise.projectschool.repository.ClassRoomRepository;
import com.exercise.projectschool.repository.StudentRepository;
import com.exercise.projectschool.repository.TeacherRepository;
import com.exercise.projectschool.service.ClassRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClassRoomServiceImpl implements ClassRoomService {
    private final ClassRoomRepository classRoomRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final CommonUtils commonUtils;

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

    @Cacheable(cacheNames = "findAllClassRoomPopolate", cacheManager = "cacheManager")
    @Override
    public ResponseEntity<List<TeacherStudentClassRoomDTO>> getAllClassRoomPopulate() {
        /** FindAll sugli oggetti classRoom-Student-Teacher*/
        List<ClassRoomEntity> classRoomEntityList = commonUtils.findAllEntities(classRoomRepository);
        List<StudentEntity> studentEntityList = commonUtils.findAllEntities(studentRepository);
        List<TeacherEntity> teacherEntitiesList = commonUtils.findAllEntities(teacherRepository);
        List <TeacherStudentClassRoomDTO> teacherStudentClassRoomListDTO = new ArrayList<>();


        if(classRoomEntityList.isEmpty()) {
            log.info("Nessuna Classe Disponibile");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        for (ClassRoomEntity classRoom: classRoomEntityList) {
            String foundClassRoom = classRoom.getClassRoom();

            List<StudentEntity> studentList = new ArrayList<>();
            List<TeacherEntity> teacherList = new ArrayList<>();

            for(StudentEntity student : studentEntityList) {
                String foundStudentClassRoom = student.getClassRoom();
                if (foundClassRoom.equalsIgnoreCase(foundStudentClassRoom)) {
                    studentList.add(student);
                }
            }
            for (TeacherEntity teacher : teacherEntitiesList) {
                String foundTeacherClassRoom = teacher.getClassRoom();
                if (foundClassRoom.equalsIgnoreCase(foundTeacherClassRoom)) {
                    teacherList.add(teacher);
                }
            }
            TeacherStudentClassRoomDTO teacherStudentDto = TeacherStudentClassRoomDTO.builder()
                    .classRoom(foundClassRoom)
                    .students(studentList)
                    .teachers(teacherList).build();

            teacherStudentClassRoomListDTO.add(teacherStudentDto);
        }

        log.info("Studenti e Insegnati associati alle proprie classi");
        return new ResponseEntity<>(teacherStudentClassRoomListDTO,HttpStatus.OK);

    }
}
