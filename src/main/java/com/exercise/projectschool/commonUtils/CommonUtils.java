package com.exercise.projectschool.commonUtils;

import com.exercise.projectschool.dto.StudentDTO;
import com.exercise.projectschool.dto.TeacherDTO;
import com.exercise.projectschool.dto.TeacherStudentDTO;
import com.exercise.projectschool.entity.StudentEntity;
import com.exercise.projectschool.entity.TeacherEntity;
import com.exercise.projectschool.model.Student;
import com.exercise.projectschool.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CommonUtils {
    public static StudentEntity buildStudentEntity(Student student,String capitalCity) {
        return StudentEntity.builder()
                .name(student.getName())
                .city(capitalCity)
                 .capital(student.getCapital())
                .age(student.getAge())
                .school(student.getSchool())
                .serialNumber(student.getSerialNumber())
                 .classRoom(student.getClassRoom()).build();
    }

    public static TeacherEntity buildTeacherEntity(Teacher teacher) {
        return TeacherEntity.builder()
                .name(teacher.getName())
                .email(teacher.getEmail())
                .matter(teacher.getMatter())
                .school(teacher.getSchool())
                .serialNumber(teacher.getSerialNumber())
                .classRoom(teacher.getClassRoom()).build();
    }

    public static TeacherStudentDTO buildTeacherStudentDTO(List<StudentEntity> studentEntities,List<TeacherEntity> teacherEntities,String school) {
        return TeacherStudentDTO.builder()
                .teachers(teacherEntities)
                .students(studentEntities)
                .school(school).build();
    }
    public static StudentDTO buildStudentDTOWithJpa(StudentEntity studentEntity) {
            StudentDTO studentDTO;
            List<TeacherEntity> teacherEntities = studentEntity.getTeacherEntityList();
            List<TeacherDTO> teacherDTOList = new ArrayList<>();

            for (TeacherEntity teacherEntity : teacherEntities) {
                TeacherDTO teacherDTO = TeacherDTO.builder()
                        .id(teacherEntity.getId())
                        .name(teacherEntity.getName())
                        .email(teacherEntity.getEmail())
                        .matter(teacherEntity.getMatter())
                        .school(teacherEntity.getSchool())
                        .serialNumber(teacherEntity.getSerialNumber())
                        .classRoom(teacherEntity.getClassRoom())
                        .students(Collections.emptyList()).build();  // Inizializza con una lista vuota
                teacherDTOList.add(teacherDTO);
            }

            studentDTO = StudentDTO.builder()
                    .id(studentEntity.getId())
                    .name(studentEntity.getName())
                    .city(studentEntity.getCity())
                    .capital(studentEntity.getCapital())
                    .age(studentEntity.getAge())
                    .school(studentEntity.getSchool())
                    .serialNumber(studentEntity.getSerialNumber())
                    .classRoom(studentEntity.getClassRoom())
                    .teachers(teacherDTOList).build();

            return studentDTO;
    }

    public static TeacherDTO buildTeacherDTOWithJpa(TeacherEntity teacherEntity) {
        TeacherDTO teacherDTO;
        List<StudentEntity> studentEntities = teacherEntity.getStudentEntityList();
        List<StudentDTO> studentDTOList = new ArrayList<>();

        for (StudentEntity studentEntity : studentEntities) {
            StudentDTO studentDTO = StudentDTO.builder()
                    .id(studentEntity.getId())
                    .name(studentEntity.getName())
                    .city(studentEntity.getCity())
                    .capital(studentEntity.getCapital())
                    .age(studentEntity.getAge())
                    .school(studentEntity.getSchool())
                    .serialNumber(studentEntity.getSerialNumber())
                    .classRoom(studentEntity.getClassRoom())
                    .teachers(Collections.emptyList()).build();  // Inizializza con una lista vuota
            studentDTOList.add(studentDTO);
        }

        teacherDTO = TeacherDTO.builder()
                .id(teacherEntity.getId())
                .name(teacherEntity.getName())
                .email(teacherEntity.getEmail())
                .matter(teacherEntity.getMatter())
                .school(teacherEntity.getSchool())
                .serialNumber(teacherEntity.getSerialNumber())
                .classRoom(teacherEntity.getClassRoom())
                .students(studentDTOList).build();

        return teacherDTO;
    }


    public <T> List<T> findAllEntities(JpaRepository<T, ?> repository) {
        return repository.findAll();
    }

}
