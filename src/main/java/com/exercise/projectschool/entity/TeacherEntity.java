package com.exercise.projectschool.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity(name = "TeacherEntity")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "teacher")
public class TeacherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "email")
    String email;

    @Column(name = "matter")
    String matter;

    @Column(name = "school")
    String school;

    @Column(name = "serialnumber")
    String serialNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacherEntity", cascade = CascadeType.ALL)
    private List<StudentEntity> StudententityList;

}
