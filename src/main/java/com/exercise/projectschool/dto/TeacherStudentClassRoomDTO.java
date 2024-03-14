package com.exercise.projectschool.dto;

import com.exercise.projectschool.entity.ClassRoomEntity;
import com.exercise.projectschool.entity.StudentEntity;
import com.exercise.projectschool.entity.TeacherEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@ApiModel(description = "TeacherStudentClassRoomDTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherStudentClassRoomDTO {
    String classRoom;

    List<StudentEntity> students;

    List<TeacherEntity> teachers;
}


