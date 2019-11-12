package com.juhyeon.aucobot;

import com.juhyeon.aucobot.service.IssueService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class AucobotApplicationTests extends IssueService {

    private static final String realCloneUrl = "https://github.com/juhyeon96/AutoComment-bot.git";
    @Test
    void contextLoads() {
    }

    @Test
    public void githubConnection() {
        super.createGitHubTemplate();
        String cloneUrl;
        cloneUrl = this.githubTemplate.repoOperations().getRepo(user, repo).getCloneUrl();

        assertEquals(cloneUrl, realCloneUrl);
    }

}
