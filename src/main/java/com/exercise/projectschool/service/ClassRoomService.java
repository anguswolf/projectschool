package com.exercise.projectschool.service;

import com.exercise.projectschool.entity.ClassRoomEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClassRoomService {
    ResponseEntity<ClassRoomEntity> addClassRoom(String classRoom);

    ResponseEntity<List<ClassRoomEntity>> getAllClassRoom();
}
