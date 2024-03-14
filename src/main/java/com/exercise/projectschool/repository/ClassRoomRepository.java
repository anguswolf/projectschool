package com.exercise.projectschool.repository;

import com.exercise.projectschool.entity.ClassRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRoomRepository extends JpaRepository<ClassRoomEntity, Long> {
    List<ClassRoomEntity> findByClassRoom(String classRoom);
}
