package com.exercise.projectschool.kafka.config;


import com.exercise.projectschool.model.Student;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.retry.annotation.EnableRetry;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@EnableRetry
@ConditionalOnProperty(prefix = "spring", name = "kafka.enabled", havingValue = "true")
public class KafkaProducerConfig {

    @Value("${spring.kafka.host}")
    private String kafkaHost;

    private static final Map<String, Object> DEFAULT_PRODUCER_CONFIG_MAP = new HashMap<>();

    @PostConstruct
    public void configureKafkaProperties() {
        DEFAULT_PRODUCER_CONFIG_MAP.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHost);
        DEFAULT_PRODUCER_CONFIG_MAP.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        DEFAULT_PRODUCER_CONFIG_MAP.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    }

    @Bean
    public KafkaTemplate<String, Student> studentKafkaProducerTemplate() {
        return new KafkaTemplate<>(studentProducerFactory());
    }

    @Bean
    public ProducerFactory<String, Student> studentProducerFactory() {
        JsonSerializer<Student> studentJsonSerializer = new JsonSerializer<Student>();
        studentJsonSerializer.setAddTypeInfo(false);
        return new DefaultKafkaProducerFactory<>(DEFAULT_PRODUCER_CONFIG_MAP, new StringSerializer(), studentJsonSerializer);
    }
}