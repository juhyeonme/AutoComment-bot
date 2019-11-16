package com.juhyeon.aucobot.bot;

import com.juhyeon.aucobot.bot.exception.InvalidBotRequestException;
import com.juhyeon.aucobot.service.GitHubIssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
@ConditionalOnMissingBean
public class CustomBotClassifier<Event> implements BotClassifier<Event> {
    private static final Logger logger = LoggerFactory.getLogger(CustomBotClassifier.class);
    private GitHubIssueService gitHubIssueService;
    private int issueNumber = 0;

    CustomBotClassifier(){}

    @Autowired
    public CustomBotClassifier(GitHubIssueService githubIssueService) {
        this.gitHubIssueService = githubIssueService;
    }

    @Override
    public BotRequest classify(Event event) {

        BotRequest botRequest = null;

        //TODO : @param event를 botRequest 형식에 적절히 parsing.
        /*
       Issue issue = this.gitHubIssueService.readNewIssue();
       this.issueNumber = issue.getNumber();
       BotRequest botRequest = BotRequest.builder()
                                    .issueNumber(String.valueOf(issueNumber))
                                    .author(issue.getUser().getLogin())
                                    .title(issue.getTitle())
                                    .body(issue.getBody())
                                    .build();



        if(!botRequest.checkValidation()) {
            return skip();
        }

         */

        return null;
    }

    private BotRequest skip() {
        throw new InvalidBotRequestException();
    }

    @Override
    public void message() {
        //TODO : 상황에 따른 message type으로 write-comment
        logger.info("[BotClassifier] Bot comments to the issue.");

        if(this.issueNumber > 0) {

            try {
                this.gitHubIssueService.createIssueComment(this.issueNumber, "안녕안녕 issue 인식 완료~!");
            } catch (IOException exception) {
                logger.error("[BotClassifier] IOException : Cannot create comment.");
            }

        }
        else {
            skip();
        }
    }
}
