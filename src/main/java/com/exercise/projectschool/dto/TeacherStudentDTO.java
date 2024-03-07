package com.exercise.projectschool.dto;

import com.exercise.projectschool.entity.StudentEntity;
import com.exercise.projectschool.entity.TeacherEntity;
import com.exercise.projectschool.model.Student;
import com.exercise.projectschool.model.Teacher;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@ApiModel(description = "TeacherStudentDTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherStudentDTO {
    String school;

    List<StudentEntity> students;

    List<TeacherEntity> teachers;
}
