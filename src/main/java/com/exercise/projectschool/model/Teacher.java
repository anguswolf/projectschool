package com.exercise.projectschool.model;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
}
