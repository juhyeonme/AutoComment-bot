package com.juhyeon.aucobot.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "spring.api.github")
public class GitHubProperties {

    private String user;
    private String password;
    private String repo;
}
