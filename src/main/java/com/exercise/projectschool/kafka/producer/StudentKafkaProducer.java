package com.exercise.projectschool.kafka.producer;


import com.exercise.projectschool.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.KafkaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class StudentKafkaProducer {

    @Autowired
    @Qualifier("studentKafkaProducerTemplate")
    private KafkaTemplate<String, Student> userKafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String topicName;

    @Value("${spring.kafka.enabled}")
    private boolean isKafkaEnabled;


    @Retryable(value = KafkaException.class, maxAttempts = 3, backoff = @Backoff(delay = 3000))
    public String send(Student student) {
        log.info("Student sending data='{}={}' to topic='{}'", student.hashCode(), student, topicName);
        String key = UUID.randomUUID().toString();
        if (isKafkaEnabled) {
            userKafkaTemplate.send(topicName, key, student);
            return "data sent successfully";
        }
        return "kafka is disabled in application";
    }

    @Recover
    public void recoverKafkaMessage(KafkaException exception) {
        log.error("sending data to error queue with message: {}", exception.getMessage());
    }

    private void logMsg(SendResult<String, String> res) {
        var recordMetadata = res.getRecordMetadata();
        log.info("send deadLineMessage " + recordMetadata.toString() + " -> partition: {} offset: {} ",
                recordMetadata.partition(), recordMetadata.offset());
    }
}