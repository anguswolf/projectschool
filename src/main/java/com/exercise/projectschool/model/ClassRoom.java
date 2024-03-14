package com.exercise.projectschool.model;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ApiModel(description = "ClassRoom")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClassRoom {
    Long id;
    String classRoom;
}
