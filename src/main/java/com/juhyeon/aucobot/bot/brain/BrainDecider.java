package com.juhyeon.aucobot.bot.brain;


import com.juhyeon.aucobot.bot.BotRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrainDecider {
    private static final Logger logger = LoggerFactory.getLogger(BrainDecider.class);

    public Knowledge execute(BotRequest botRequest) {
        return conversation(botRequest).execute(botRequest);
    }


    @SuppressWarnings("unchecked")
    private <T extends SuperBrainStorming> T conversation(BotRequest botRequest) {
        logger.info("[BrainDecider] Starts conversation ... ");
        String content = botRequest.getBody();

        return (T) new SuperBrainStorming() {
            Knowledge knowledge = decide(content);
            @Override
            public Knowledge execute(BotRequest botRequest) {
                return Knowledge.builder()
                                .code(knowledge.getCode())
                                .type(knowledge.getType())
                                .message(knowledge.getMessage())
                                .build();
            }

            @Override
            String mention() {
                return null;
            }

        };
    }

    private Knowledge decide(String content) {
        //TODO : if content contain ...
        Knowledge knowledge = new Knowledge();

        return knowledge;
    }
}
