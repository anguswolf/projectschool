package com.exercise.projectschool;

import com.exercise.projectschool.config.RSAKeyRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RSAKeyRecord.class)
@SpringBootApplication
public class ProjectschoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectschoolApplication.class, args);
    }

}
