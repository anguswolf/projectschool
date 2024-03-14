package com.exercise.projectschool.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "classroom")
    String classRoom;

    //@JsonIgnore
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "teacherList", cascade = CascadeType.ALL)
    List<ClassRoomEntity> classRoomList;

    @JsonIgnore
    @JsonManagedReference
    @ManyToMany(mappedBy = "teacherEntityList", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    List<StudentEntity> StudententityList;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher", cascade = CascadeType.ALL)
    List<CourseBookingEntity> courseBookingEntityList;
}
