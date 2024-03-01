package com.exercise.projectschool.commonUtils;

import com.exercise.projectschool.entity.StudentEntity;
import com.exercise.projectschool.model.Student;
import org.springframework.stereotype.Component;

@Component
public class CommonUtils {
    public static StudentEntity buildStudent(Student student) {
         StudentEntity studentEntity = StudentEntity.builder()
                .name(student.getName())
                .city(student.getCity())
                .age(student.getAge())
                .school(student.getSchool())
                .serialNumber(student.getSerialNumber()).build();
         return studentEntity;
    }
}
