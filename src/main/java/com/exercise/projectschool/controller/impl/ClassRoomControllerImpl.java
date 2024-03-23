package com.exercise.projectschool.controller.impl;

import com.exercise.projectschool.controller.ClassRoomController;
import com.exercise.projectschool.dto.TeacherStudentClassRoomDTO;
import com.exercise.projectschool.entity.ClassRoomEntity;
import com.exercise.projectschool.service.ClassRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping(path = "/classroom")
public class ClassRoomControllerImpl implements ClassRoomController {
    private final ClassRoomService classRoomService;
    @PostMapping(path = "/add/{classRoom}")
    public ResponseEntity<ClassRoomEntity> addClassRoom(@PathVariable String classRoom) {
        return classRoomService.addClassRoom(classRoom);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<ClassRoomEntity>> getAllClassRoom() {
        return classRoomService.getAllClassRoom();
    }

    @GetMapping(path = "/all/classfull")
    public ResponseEntity<List<TeacherStudentClassRoomDTO>> getAllClassRoomPopulate() {
        return classRoomService.getAllClassRoomPopulate();
    }
}
