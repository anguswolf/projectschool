package com.exercise.projectschool.kafka.config;

import com.exercise.projectschool.model.Student;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.retry.annotation.EnableRetry;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@EnableRetry
@ConditionalOnProperty(prefix = "spring", name = "kafka.enabled", havingValue = "true")
public class KafkaConsumerConfig {

    @Value("${spring.kafka.host}")
    private String kafkaHost;

    @Value("${spring.kafka.consumer.max-poll-records}")
    private int maxPollRecord;

    @Value("${spring.kafka.consumer-group-id}")
    private String kafkaConsumerGroupId;

    private static final Map<String, Object> DEFAULT_CONSUMER_CONFIG_MAP = new HashMap<>();

    @PostConstruct
    public void configureKafkaProperties() {
        DEFAULT_CONSUMER_CONFIG_MAP.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHost);
        DEFAULT_CONSUMER_CONFIG_MAP.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        DEFAULT_CONSUMER_CONFIG_MAP.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        DEFAULT_CONSUMER_CONFIG_MAP.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecord);
    }

    @Bean
    public ConsumerFactory<String, Student> studentConsumerFactory() {
        Map<String, Object> map = new HashMap<>(DEFAULT_CONSUMER_CONFIG_MAP);
        map.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConsumerGroupId);
        JsonDeserializer<Student> studentJsonDeserializer = new JsonDeserializer<>(Student.class);
        studentJsonDeserializer.ignoreTypeHeaders();
        return new DefaultKafkaConsumerFactory<>(map, new StringDeserializer(), studentJsonDeserializer);

    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Student> concurrentKafkaListenerStudentConsumerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Student> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(studentConsumerFactory());
        return factory;
    }
}