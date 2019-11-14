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
public class Event {
    private static final Logger logger = LoggerFactory.getLogger(Event.class);
    private BotClassifier classifier;

    Event() {}

    public Event(BotClassifier classifier) {
        this.classifier = classifier;
    }

    public void execute() {
        try {
            BotRequest botRequest = classifier.classify();

            if(ObjectUtils.isEmpty(botRequest)) {
                return;
            }

            classifier.message();

        } catch(InvalidBotRequestException e) {
            logger.error("[Bot-Event] InvalidBotRequestException : classify || message");
            //TODO : Exception Handling
        }
    }
}
