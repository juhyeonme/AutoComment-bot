package com.juhyeon.aucobot.bot;

import com.juhyeon.aucobot.bot.event.IEvent;
import com.juhyeon.aucobot.bot.event.IEventQueue;
import com.juhyeon.aucobot.service.CustomIssueEventSensor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskRunner {
    private static final Logger logger = LoggerFactory.getLogger(TaskRunner.class);
    private final static int BOT_SCHEDULE_PERIOD = 30;
    private ThreadPoolTaskExecutor executor;
    private IEventQueue iEventQueue;
    private List<CustomIssueEventSensor> eventSensors;

    TaskRunner() {}

    @Autowired
    public TaskRunner(IEventQueue iEventQueue, List<CustomIssueEventSensor> eventSensors) {
        this.iEventQueue = iEventQueue;
        this.eventSensors = Optional.ofNullable(eventSensors).orElse(Collections.emptyList());
    }

    @Autowired
    @Qualifier(value = "aucobotThreadPoolManager")
    public void setIssueEventSensor(ThreadPoolTaskExecutor executor) {
        this.executor = executor;
    }

    @Scheduled(fixedDelay = BOT_SCHEDULE_PERIOD)
    private void execute() {
        while(iEventQueue.checkEmpty()) {
            IEvent event = iEventQueue.poll();
            executor.execute(new TaskPoller(event));
        }
    }

    @Scheduled(fixedDelay = BOT_SCHEDULE_PERIOD)
    public void sensingIEvent() {
        for(CustomIssueEventSensor eventSensor : eventSensors) {
            LinkedList<IEvent> sensedEvents = (LinkedList<IEvent>) eventSensor.sensingEvent();

            for(IEvent item : sensedEvents) {
                this.iEventQueue.offer(item);
            }
        }
    }

    public static class TaskPoller implements Runnable {
        IEvent event;
        TaskPoller(IEvent event) {
            this.event = event;
        }

        @Override
        public void run() {
            try {
                this.event.execute();
            }
            catch(Exception e) {
                logger.warn("[TaskPoller-Runner] Exception while execute runner.run() ");
            }
        }
    }

}
