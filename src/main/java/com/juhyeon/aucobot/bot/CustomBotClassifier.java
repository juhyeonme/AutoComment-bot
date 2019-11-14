package com.juhyeon.aucobot.bot;

import com.juhyeon.aucobot.service.GitHubIssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomBotClassifier implements BotClassifier {
    private static final Logger logger = LoggerFactory.getLogger(CustomBotClassifier.class);
    private GitHubIssueService gitHubIssueService;

    @Autowired
    public CustomBotClassifier(GitHubIssueService githubIssueService) {
        this.gitHubIssueService = githubIssueService;
    }

    @Override
    public BotRequest classify() {

    }

    @Override
    public void message() {

    }
}
