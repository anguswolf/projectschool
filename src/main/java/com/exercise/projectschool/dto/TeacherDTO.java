package com.exercise.projectschool.dto;

import com.exercise.projectschool.model.Student;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@ApiModel(description = "TeacherDTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherDTO {
    Long id;

    String name;

    String email;

    String matter;

    String school;

    String serialNumber;

    String classRoom;

    List<StudentDTO> students;
}
