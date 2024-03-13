package com.exercise.projectschool.model;

import com.exercise.projectschool.entity.TeacherEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ApiModel(description = "Student")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {
    Long id;
    String name;
    String city;
    String capital;
    String age;
    String school;
    String serialNumber;
    String classRoom;
    TeacherEntity teacherEntity;
}
