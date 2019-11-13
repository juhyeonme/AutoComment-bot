package com.juhyeon.aucobot;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@PropertySource("classpath:application.yml")
class AucobotApplicationTests {

    @Value("${spring.api.github.user}")
    private String user;

    @Value("${spring.api.github.password}")
    private String password;

    @Value("${spring.api.github.repo}")
    private String repo;

    @Test
    void contextLoads() {
    }

    @Test
    public void connectEGitAndMyRepo() throws Exception {
        String realCloneUrl = "https://github.com/juhyeon96/AutoComment-bot.git";

        GitHubClient githubClient = new GitHubClient();
        githubClient.setCredentials(user, password);
        RepositoryService service = new RepositoryService();
        Repository repository = service.getRepository(user, repo);

        assertEquals(realCloneUrl, repository.getCloneUrl());
        assertEquals(1, repository.getOpenIssues());
    }

}
