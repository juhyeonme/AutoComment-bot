package com.juhyeon.aucobot.bot.event;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class EventQueue {
    private Queue<Event> queue;

    public EventQueue() {
        queue = new ConcurrentLinkedQueue<>();
    }

    public boolean checkEmpty() {
        return queue.size() > 0;
    }

    public void offer(Event event) {
        if(event == null)
            return;

        queue.offer(event);
    }

    public Event poll() {
        if(queue!=null && queue.size()>0) {
            return queue.poll();
        }

        return null;
    }
}
