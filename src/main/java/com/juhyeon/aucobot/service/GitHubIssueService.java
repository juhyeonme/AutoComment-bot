package com.juhyeon.aucobot.service;

import com.juhyeon.aucobot.config.GitHubProperties;
import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.PageIterator;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.service.EventService;
import org.eclipse.egit.github.core.service.IssueService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;


@Repository
@ConditionalOnProperty(prefix = "spring.api.github", name = "github-info")
@EnableConfigurationProperties(GitHubProperties.class)
@ConditionalOnWebApplication
public class GitHubIssueService {

    private final static Logger logger = LoggerFactory.getLogger(GitHubIssueService.class);
    private IssueService issueService;
    private RepositoryService repositoryService;
    private EventService eventService;
    private GitHubClient client;
    private org.eclipse.egit.github.core.Repository repository;
    private String owner;
    private String repo;

    GitHubIssueService() {}

    @Autowired
    public GitHubIssueService(GitHubProperties gitHubProperties) throws IOException {
        logger.info("[RepositoryManager] Initializing GitHub-RepositoryManager for the repository ... ");

        this.owner = gitHubProperties.getUser();
        this.repo = gitHubProperties.getRepo();

        this.client = new GitHubClient();
        this.client.setCredentials(this.owner, gitHubProperties.getPassword());

        this.issueService = new IssueService(this.client);
        this.repositoryService = new RepositoryService(this.client);
        this.repository = this.repositoryService.getRepository(this.owner, this.repo);
        this.eventService = new EventService(this.client);
    }

    public GitHubClient getGitHubClient() {
        return this.client;
    }

    public PageIterator<Event> getIterableEvent() throws IOException {
        logger.info("[GitHubService] Try to get events.");
        PageIterator<Event> iterableEvents = null;

        if(this.repository.getOpenIssues() > 0) {
            iterableEvents = this.eventService.pageEvents(this.repository);

            if(iterableEvents != null && iterableEvents.hasNext()) {
                return iterableEvents;
            }
        }

        return null;
    }

    public void createIssueComment(int issueId, String comment) throws IOException {
        logger.info("[GitHubService] Bot creates COMMENT of an issue.");
        this.issueService.createComment(this.owner, this.repo, issueId, comment);
    }
}
