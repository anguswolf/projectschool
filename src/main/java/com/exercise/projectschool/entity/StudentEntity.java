package com.exercise.projectschool.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity(name = "StudentEntity")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "student")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "city")
    String city;

    @Column(name = "capital")
    String capital;

    @Column(name = "age")
    String age;

    @Column(name = "school")
    String school;

    @Column(name = "serialnumber")
    String serialNumber;

    @Column(name = "classroom")
    String classRoom;

    /*//@JsonIgnore
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    ClassRoomEntity classRoomRelation;*/

    //@JsonBackReference
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    List<TeacherEntity> teacherEntityList;
}
