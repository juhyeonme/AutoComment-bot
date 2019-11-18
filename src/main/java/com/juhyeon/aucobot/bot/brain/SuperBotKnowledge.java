package com.juhyeon.aucobot.bot.brain;

import com.juhyeon.aucobot.bot.BotRequest;

public abstract class SuperBotKnowledge {
    abstract Knowledge execute(BotRequest botRequest);
    abstract String mention();

}
