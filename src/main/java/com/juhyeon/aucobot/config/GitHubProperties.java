package com.juhyeon.aucobot.config;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@NoArgsConstructor
@ConfigurationProperties("spring.api.github")
@Configuration
public class GitHubProperties {

    private String user;
    private String password;
    private String repo;
}
