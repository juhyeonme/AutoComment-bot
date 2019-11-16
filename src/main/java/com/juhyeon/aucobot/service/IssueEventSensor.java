package com.juhyeon.aucobot.service;

import com.juhyeon.aucobot.bot.event.Event;

import java.util.List;

public interface IssueEventSensor {
    List<Event> sensingEvent(GitHubIssueService service);
}
