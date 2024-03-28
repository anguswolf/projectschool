package com.exercise.projectschool.repository;


import com.exercise.projectschool.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUsername(String username);
}
