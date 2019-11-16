package com.juhyeon.aucobot.service;

import com.juhyeon.aucobot.bot.event.Event;
import org.eclipse.egit.github.core.client.PageIterator;
import org.eclipse.egit.github.core.event.IssuesPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Service
public class CustomIssueEventSensor implements IssueEventSensor {

    private final static Logger logger = LoggerFactory.getLogger(CustomIssueEventSensor.class);
    private final static String ISSUE_EVENT_TYPE = "IssuesEvent";
    private final static String ISSUE_EVENT_PAYLOAD_ACTION = "opened";
    private final static int BOT_SCHEDULE_PERIOD = 30;

    @Override
    public List<Event> sensingEvent(GitHubIssueService service) {
        PageIterator<org.eclipse.egit.github.core.event.Event> iterableEvents;
        LinkedList<org.eclipse.egit.github.core.event.Event> eventsOnSamePage;
        LinkedList<Event> checkedEvents = new LinkedList<>();

        try {
            iterableEvents = service.getIterableEvent();

            while(iterableEvents.hasNext()) {
                eventsOnSamePage = new LinkedList<>(iterableEvents.next());

                sensingIssueEvent(eventsOnSamePage, checkedEvents);
            }
        }
        catch(IOException exception) {
            //exception handling
        }

        return Collections.emptyList();
    }

    private LinkedList<Event> sensingIssueEvent(LinkedList<org.eclipse.egit.github.core.event.Event> eventsOnSamePage,
                                                LinkedList<Event> checkedEvents) {

        long now = new Date().getTime();
        long eventTime;

        for(org.eclipse.egit.github.core.event.Event item : eventsOnSamePage) {
            eventTime = item.getCreatedAt().getTime();
            IssuesPayload payload = (IssuesPayload) item.getPayload();

            if(now-eventTime < BOT_SCHEDULE_PERIOD+1) {
                
            }
            else {
                //break
            }
        }

        return checkedEvents;
    }

}
