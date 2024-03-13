package com.exercise.projectschool.model;

import com.exercise.projectschool.entity.ClassRoomEntity;
import com.exercise.projectschool.entity.StudentEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@ApiModel(description = "Teacher")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Teacher {
    Long id;
    String name;
    String email;
    String matter;
    String school;
    String serialNumber;
    String classRoom;
    List<ClassRoomEntity> classRoomList;
    List<StudentEntity> StudententityList;
}
