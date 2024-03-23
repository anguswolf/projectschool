package com.exercise.projectschool.controller;

import com.exercise.projectschool.dto.TeacherStudentClassRoomDTO;
import com.exercise.projectschool.entity.ClassRoomEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ClassRoomController {
    @PostMapping(path = "/add/{classRoom}")
    ResponseEntity<ClassRoomEntity> addClassRoom(@PathVariable String classRoom);

    @GetMapping(path = "/all")
    ResponseEntity<List<ClassRoomEntity>> getAllClassRoom();

    @GetMapping(path = "/all/classfull")
    ResponseEntity<List<TeacherStudentClassRoomDTO>> getAllClassRoomPopulate();
}
