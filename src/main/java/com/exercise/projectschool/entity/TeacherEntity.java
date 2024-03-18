package com.exercise.projectschool.entity;

import com.fasterxml.jackson.annotation.*;
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

   /*//@JsonIgnore
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "teacherList", cascade = CascadeType.ALL)
    List<ClassRoomEntity> classRoomList;*/


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
            @JoinTable(name = "TEACHER_STUDENT_MAPPING", joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    //@JsonBackReference
    //@JsonIgnoreProperties("teacherEntityList")
    List<StudentEntity> StudentEntityList;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher", cascade = CascadeType.ALL)
    List<CourseBookingEntity> courseBookingEntityList;
}
