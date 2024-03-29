package com.juhyeon.aucobot.bot;

import com.juhyeon.aucobot.bot.exception.InvalidBotRequestException;
import com.juhyeon.aucobot.service.GitHubIssueService;
import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.event.IssuesPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class CustomBotClassifier<Event> implements BotClassifier<Event> {
    private static final Logger logger = LoggerFactory.getLogger(CustomBotClassifier.class);
    private GitHubIssueService gitHubIssueService;

    CustomBotClassifier(){}

    @Autowired
    public CustomBotClassifier(GitHubIssueService githubIssueService) {
        this.gitHubIssueService = githubIssueService;
    }

    @Override
    public BotRequest classify(Event event) {
        BotRequest botRequest = null;

        if(event instanceof org.eclipse.egit.github.core.event.Event) {
            IssuesPayload payload = (IssuesPayload)((org.eclipse.egit.github.core.event.Event) event).getPayload();
            Issue issue = payload.getIssue();

            botRequest = BotRequest.builder()
                    .issueNumber(String.valueOf(issue.getNumber()))
                    .author(issue.getUser().getLogin())
                    .title(issue.getTitle())
                    .body(issue.getBody())
                    .build();

            if (!botRequest.checkValidation()) {
                return skip();
            }

            return botRequest;
        }

        return null;
    }

    private BotRequest skip() {
        throw new InvalidBotRequestException();
    }

    @Override
    public void message(String issueNumber) {
        //TODO : 상황에 따른 message type으로 write-comment
        logger.info("[BotClassifier] Bot comments to the issue.");

        if(Integer.parseInt(issueNumber) > 0) {

            try {
                this.gitHubIssueService.createIssueComment(Integer.parseInt(issueNumber), "안녕안녕 issue 인식 완료~!");
            } catch (IOException exception) {
                logger.error("[BotClassifier] IOException : Cannot create comment.");
            }

        }
        else {
            skip();
        }
    }
}
