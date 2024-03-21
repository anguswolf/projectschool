package com.exercise.projectschool.dto;

import com.exercise.projectschool.entity.TeacherEntity;
import com.exercise.projectschool.model.Teacher;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@ApiModel(description = "StudentDTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentDTO {
    Long id;

    String name;

    String city;

    String capital;

    String age;

    String school;

    String serialNumber;

    String classRoom;

    List<TeacherDTO> teachers;
}
