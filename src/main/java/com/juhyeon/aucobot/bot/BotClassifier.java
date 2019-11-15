package com.juhyeon.aucobot.bot;

import com.juhyeon.aucobot.bot.event.Event;

public interface BotClassifier<T> {
    BotRequest classify(T event);
    //Todo: Response Type 정의 -> switch로 분류해서 write comment
    void message();
}
