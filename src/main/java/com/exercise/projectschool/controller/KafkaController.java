package com.exercise.projectschool.controller;

import com.exercise.projectschool.model.Student;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface KafkaController {
    @PostMapping(path = "/student")
    String sendData(@RequestBody Student student);
}
