package com.juhyeon.aucobot.service;

import com.juhyeon.aucobot.domain.Issue;

public interface IssueService {
    Issue submitIssue(Issue issue);

    Issue deleteIssue(Long id, String authorEmail);
}
