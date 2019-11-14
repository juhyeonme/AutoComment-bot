package com.juhyeon.aucobot.bot;

public interface BotClassifier {
    BotRequest classify();
    //Todo: Response Type 정의 -> switch로 분류해서 write comment
    void message();
}
