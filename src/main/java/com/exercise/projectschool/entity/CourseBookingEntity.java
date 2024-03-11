package com.exercise.projectschool.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.OffsetDateTime;

@Entity(name = "CourseBookingEntity")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "coursebooking")
public class CourseBookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "start_booking")
    OffsetDateTime startBooking;

    @Column(name = "end_booking")
    OffsetDateTime endBooking;

    @Column(name = "classroom")
    String classRoom;

    //@JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacherentity_id")
    TeacherEntity teacher;
}
