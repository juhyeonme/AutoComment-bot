package com.juhyeon.aucobot.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@NoArgsConstructor
@ConfigurationProperties("spring.social.github")
public class GitHubProperties {

    private String clientId;
    private String clientSecret;

}
