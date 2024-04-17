package com.exercise.projectschool.controller.impl;

import com.exercise.projectschool.kafka.producer.StudentKafkaProducer;
import com.exercise.projectschool.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaControllerImpl {
    private final StudentKafkaProducer userKafkaProducer;

    @PostMapping(path = "/student")
    public String sendData(@RequestBody Student student) {
        return userKafkaProducer.send(student);
    }

}
