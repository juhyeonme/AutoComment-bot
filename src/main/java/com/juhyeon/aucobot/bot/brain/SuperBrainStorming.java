package com.juhyeon.aucobot.bot.brain;

import com.juhyeon.aucobot.bot.BotRequest;

public interface SuperBrainStorming {
    Knowledge execute(BotRequest botRequest);
    String mention();

}
