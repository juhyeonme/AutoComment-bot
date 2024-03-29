package com.juhyeon.aucobot.bot.event;

import com.juhyeon.aucobot.bot.BotClassifier;
import com.juhyeon.aucobot.bot.BotRequest;
import com.juhyeon.aucobot.bot.exception.InvalidBotRequestException;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

@Setter
@Getter
public class IEvent<T> {
    private static final Logger logger = LoggerFactory.getLogger(IEvent.class);
    private BotClassifier<T> classifier;
    private T item;

    IEvent() {}

    public IEvent(BotClassifier<T> classifier, T item) {
        this.classifier = classifier;
        this.item = item;
    }

    public void execute() {
        try {
            BotRequest botRequest = classifier.classify(item);

            if(ObjectUtils.isEmpty(botRequest)) {
                return;
            }

            classifier.message(botRequest.getIssueNumber());

        } catch(InvalidBotRequestException e) {
            logger.error("[Bot-Event] InvalidBotRequestException : classify || message");
            //TODO : Exception Handling
        }
    }
}
