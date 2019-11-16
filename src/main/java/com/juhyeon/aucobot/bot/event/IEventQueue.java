package com.juhyeon.aucobot.bot.event;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class IEventQueue {
    private Queue<IEvent> queue;

    public IEventQueue() {
        queue = new ConcurrentLinkedQueue<>();
    }

    public boolean checkEmpty() {
        return queue.size() > 0;
    }

    public void offer(IEvent event) {
        if(event == null)
            return;

        queue.offer(event);
    }

    public IEvent poll() {
        if(queue!=null && queue.size()>0) {
            return queue.poll();
        }

        return null;
    }
}
