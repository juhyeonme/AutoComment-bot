package com.juhyeon.aucobot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.social.github.api.impl.GitHubTemplate;
import org.springframework.stereotype.Service;


@Service
@PropertySource("classpath:application.yml")
public class IssueService {
    private static final String BASE_URL = "https://api.github.com";
    protected GitHubTemplate githubTemplate;

    @Value("${spring.api.github.access-token}")
    protected String githubAccessToken;

    @Value("${spring.api.github.user}")
    protected String user;

    @Value("${spring.api.github.repo}")
    protected String repo;

    @Bean
    public void createGitHubTemplate() {
        this.githubTemplate = new GitHubTemplate(this.githubAccessToken);
    }


}
