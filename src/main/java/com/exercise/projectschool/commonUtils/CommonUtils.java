package com.exercise.projectschool.commonUtils;

import com.exercise.projectschool.dto.TeacherStudentDTO;
import com.exercise.projectschool.entity.StudentEntity;
import com.exercise.projectschool.entity.TeacherEntity;
import com.exercise.projectschool.model.Student;
import com.exercise.projectschool.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommonUtils {
    public static StudentEntity buildStudentEntity(Student student,String capitalCity) {
         StudentEntity studentEntity = StudentEntity.builder()
                .name(student.getName())
                .city(capitalCity)
                 .capital(student.getCapital())
                .age(student.getAge())
                .school(student.getSchool())
                .serialNumber(student.getSerialNumber())
                 .classRoom(student.getClassRoom()).build();
         return studentEntity;
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

    public <T> List<T> findAllEntities(JpaRepository<T, ?> repository) {
        return repository.findAll();
    }

}
