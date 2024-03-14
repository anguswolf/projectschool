package com.exercise.projectschool.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity(name = "ClassRoomEntity")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "classroom")
public class ClassRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "classroom")
    String classRoom;

    @JsonBackReference
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    List<TeacherEntity> teacherList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "studententity_id")
    List<StudentEntity> studentList;
}
