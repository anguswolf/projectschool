package com.exercise.projectschool.commonUtils;

import com.exercise.projectschool.entity.StudentEntity;
import com.exercise.projectschool.entity.TeacherEntity;
import com.exercise.projectschool.model.Student;
import com.exercise.projectschool.model.Teacher;
import org.springframework.stereotype.Component;

@Component
public class CommonUtils {
    public static StudentEntity buildStudentEntity(Student student) {
         StudentEntity studentEntity = StudentEntity.builder()
                .name(student.getName())
                .city(student.getCity())
                .age(student.getAge())
                .school(student.getSchool())
                .serialNumber(student.getSerialNumber()).build();
         return studentEntity;
    }

    public static TeacherEntity buildTeacherEntity(Teacher teacher) {
        TeacherEntity teacherEntity = TeacherEntity.builder()
                .name(teacher.getName())
                .email(teacher.getEmail())
                .matter(teacher.getMatter())
                .school(teacher.getSchool())
                .serialNumber(teacher.getSerialNumber()).build();
        return teacherEntity;
    }
}
