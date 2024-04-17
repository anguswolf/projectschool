package com.exercise.projectschool.kafka.consumer;

import com.exercise.projectschool.entity.StudentEntity;
import com.exercise.projectschool.model.Student;
import com.exercise.projectschool.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.KafkaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class StudentKafkaConsumer {

    @Value("${spring.kafka.topic}")
    private String topicName;

    @Autowired
    @Qualifier("studentKafkaProducerTemplate")
    private KafkaTemplate<String, Student> studentKafkaTemplate;

    private final StudentRepository studentRepository;

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.consumer-group-id}", containerFactory = "concurrentKafkaListenerStudentConsumerFactory")
    @Retryable(value = KafkaException.class, maxAttempts = 3, backoff = @Backoff(delay = 3000))
    public void receive(Student student) {
        log.info("Student received='{}={}' from {}", student.hashCode(), student, topicName);
        StudentEntity studentEntity = StudentEntity.builder()
                .id(student.getId())
                .name(student.getName())
                .city(student.getCity())
                .capital(student.getCapital())
                .age(student.getAge())
                .school(student.getSchool())
                .serialNumber(student.getSerialNumber())
                .classRoom(student.getClassRoom()).build();
        studentRepository.save(studentEntity);
    }
}