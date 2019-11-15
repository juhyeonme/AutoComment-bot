package com.juhyeon.aucobot.service;

import com.juhyeon.aucobot.bot.event.Event;
import org.eclipse.egit.github.core.client.PageIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


@Service
public class CustomIssueEventSensor implements IssueEventSensor {

    private final static Logger logger = LoggerFactory.getLogger(CustomIssueEventSensor.class);
    private final static String ISSUE_EVENT_TYPE = "IssuesEvent";
    private final static String ISSUE_EVENT_PAYLOAD_ACTION = "opened";


    @Override
    public List<Event> sensingIssueEvent(GitHubIssueService service) {
        PageIterator<org.eclipse.egit.github.core.event.Event> iterableEvents;
        LinkedList<org.eclipse.egit.github.core.event.Event> eventsOnSamePage;
        LinkedList<Event> checkedEvents = new LinkedList<>();

        try {
            iterableEvents = service.getIterableEvent();

            while(iterableEvents.hasNext()) {
                eventsOnSamePage = new LinkedList<>(iterableEvents.next());
                //method call : "IssueEvent" 타입만 걸러서 재저장.
            }
        }
        catch(IOException exception) {
            //exception handling
        }

        return Collections.emptyList();
    }

}
