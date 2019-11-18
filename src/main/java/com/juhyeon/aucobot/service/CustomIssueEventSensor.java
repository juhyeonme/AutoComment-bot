package com.juhyeon.aucobot.service;

import com.juhyeon.aucobot.bot.CustomBotClassifier;
import com.juhyeon.aucobot.bot.event.IEvent;
import lombok.RequiredArgsConstructor;
import org.eclipse.egit.github.core.client.PageIterator;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.IssuesPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CustomIssueEventSensor implements IssueEventSensor {

    private final static Logger logger = LoggerFactory.getLogger(CustomIssueEventSensor.class);
    private CustomBotClassifier<Event> botClassifier;
    private GitHubIssueService service;
    private final static String ISSUE_EVENT_TYPE = "IssuesEvent";
    private final static String OPENED_ISSUE_ACTION = "opened";
    private final static int BOT_SCHEDULE_PERIOD = 30;
    private LinkedList<Event> checkedEvents;

    @Autowired
    public void setCustomBotClassifier(CustomBotClassifier<Event> botClassifier, GitHubIssueService service) {
        this.botClassifier = botClassifier;
    }

    @Override
    public List<IEvent> sensingEvent() {
        PageIterator<Event> iterableEvents;
        LinkedList<Event> eventsOnSamePage;
        this.checkedEvents = new LinkedList<>();

        try {
            iterableEvents = this.service.getIterableEvent();

            while(iterableEvents.hasNext()) {
                eventsOnSamePage = new LinkedList<>(iterableEvents.next());

                if(! sensingIssueEvent(eventsOnSamePage))
                    break;

            }
        }
        catch(IOException exception) {
            //exception handling
        }

        if(!ObjectUtils.isEmpty(this.checkedEvents)) {
            return this.checkedEvents
                        .stream()
                        .map(event -> new IEvent<>(botClassifier, event))
                        .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    private boolean sensingIssueEvent(LinkedList<Event> eventsOnSamePage) {
        boolean isContinued = true;
        long now = new Date().getTime();
        long eventTime;

        for(Event item : eventsOnSamePage) {
            eventTime = item.getCreatedAt().getTime();

            if(now-eventTime <= BOT_SCHEDULE_PERIOD) {
                if(isOpenedIssue(item)) {
                    this.checkedEvents.add(item);
                }

            }
            else {
                isContinued = false;
                break;
            }
        }

        return isContinued;
    }

    private boolean isOpenedIssue(Event item) {
        boolean openedIssue = false;

        if(item.getType().equals(ISSUE_EVENT_TYPE)) {
            IssuesPayload payload = (IssuesPayload) item.getPayload();
            String action = payload.getAction();

            if(action.equals(OPENED_ISSUE_ACTION)) {
                logger.info("[IssueEventSensor] Sensing issue-event SUCCESSFULLY ! ");
                openedIssue = true;
            }
        }
        return openedIssue;
    }

}
