package com.juhyeon.aucobot.service;

import com.juhyeon.aucobot.domain.Issue;
import com.juhyeon.aucobot.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class IssueServiceImpl implements IssueService {
    private IssueRepository issueRepository;

    @Autowired
    public void setIssueRepository(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @Override
    public Issue submitIssue(Issue issue) {
        return this.issueRepository.save(issue);
    }

    @Override
    public Issue deleteIssue(Long id, String authorEmail) {
        Issue issue;
        issue = this.issueRepository.findById(id)
                                    .orElseThrow(EntityNotFoundException::new);
        if(authorEmail.equals(issue.getAuthorEmail())) {
            this.issueRepository.deleteById(id);
            return issue;
        }

        return null;
    }
}
