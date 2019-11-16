package com.juhyeon.aucobot.service;

import com.juhyeon.aucobot.bot.event.IEvent;

import java.util.List;

public interface IssueEventSensor {
    List<IEvent> sensingEvent(GitHubIssueService service);
}
