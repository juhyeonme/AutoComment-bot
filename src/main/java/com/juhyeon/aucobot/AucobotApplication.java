package com.juhyeon.aucobot;

import com.juhyeon.aucobot.config.GitHubProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(GitHubProperties.class)
public class AucobotApplication {

    public static void main(String[] args) {
        SpringApplication.run(AucobotApplication.class, args);
    }

}
