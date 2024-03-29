package com.exercise.projectschool.repository;

import com.exercise.projectschool.entity.UserInfoEntity;
import io.micrometer.observation.ObservationFilter;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfoEntity,Long> {
    Optional<UserInfoEntity> findByEmailId(String emailId);
}