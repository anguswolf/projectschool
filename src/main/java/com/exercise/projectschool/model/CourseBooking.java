package com.exercise.projectschool.model;

import com.exercise.projectschool.entity.TeacherEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.OffsetDateTime;

@ApiModel(description = "CourseBooking")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseBooking {
    Long id;
    OffsetDateTime startBooking;
    OffsetDateTime endBooking;
    String classRoom;
    TeacherEntity teacher;
}
