package com.juhyeon.aucobot.config;

import lombok.Builder;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Builder
@ConfigurationProperties("spring.social.github")
public class GitHubProperties {

    private String clientId;
    private String clientSecret;

}
